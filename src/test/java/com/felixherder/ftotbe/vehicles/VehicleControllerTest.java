package com.felixherder.ftotbe.vehicles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felixherder.ftotbe.auth.JwtAuthFilter;
import com.felixherder.ftotbe.config.TestConfig;
import com.felixherder.ftotbe.config.TestSecurityConfig;
import com.felixherder.ftotbe.exceptions.NotFoundException;
import com.felixherder.ftotbe.vehicles.enums.EngineType;
import com.felixherder.ftotbe.vehicles.enums.FuelType;
import com.felixherder.ftotbe.vehicles.enums.TransmissionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = VehicleController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthFilter.class))
@Import({TestConfig.class, TestSecurityConfig.class})
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VehicleService vehicleService;

    private VehicleDetailsDTO vehicleDetailsDTO;
    private String uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID().toString();
        vehicleDetailsDTO = VehicleDetailsDTO.builder()
                .uuid(uuid)
                .model("Model S")
                .make("Tesla")
                .year(Year.of(2022))
                .licensePlate("ABC-123")
                .color("Red")
                .engineType(EngineType.ELECTRIC)
                .fuelType(FuelType.ELECTRIC)
                .transmissionType(TransmissionType.AUTOMATIC)
                .instructors(Set.of())
                .trainees(Set.of())
                .sessions(Set.of())
                .build();
    }

    @Test
    void getAll_ShouldReturnListOfVehicles() throws Exception {
        VehicleSummaryDTO summary = VehicleSummaryDTO.builder()
                .uuid(uuid)
                .model("Model S")
                .make("Tesla")
                .build();

        when(vehicleService.getAll()).thenReturn(List.of(summary));

        mockMvc.perform(get("/api/v1/vehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value(uuid))
                .andExpect(jsonPath("$[0].model").value("Model S"));
    }

    @Test
    void getByUuid_Success() throws Exception {
        when(vehicleService.getByUuid(uuid)).thenReturn(vehicleDetailsDTO);

        mockMvc.perform(get("/api/v1/vehicles/{uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid))
                .andExpect(jsonPath("$.model").value("Model S"));
    }

    @Test
    void getByUuid_NotFound() throws Exception {
        when(vehicleService.getByUuid(uuid)).thenThrow(new NotFoundException("Vehicle not found"));

        mockMvc.perform(get("/api/v1/vehicles/{uuid}", uuid))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Vehicle not found"));
    }

    @Test
    void createVehicle_Success() throws Exception {
        when(vehicleService.createVehicle(any(VehicleDetailsDTO.class))).thenReturn(vehicleDetailsDTO);

        mockMvc.perform(post("/api/v1/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleDetailsDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid").value(uuid));
    }

    @Test
    void createVehicle_ValidationError() throws Exception {
        VehicleDetailsDTO invalidVehicle = VehicleDetailsDTO.builder()
                .model("") // Invalid
                .make("")  // Invalid
                .build();

        mockMvc.perform(post("/api/v1/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidVehicle)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed!"))
                .andExpect(jsonPath("$.errors.model").exists())
                .andExpect(jsonPath("$.errors.make").exists());
    }

    @Test
    void updateVehicleDetails_Success() throws Exception {
        when(vehicleService.updateVehicleDetails(eq(uuid), any(VehicleDetailsDTO.class))).thenReturn(vehicleDetailsDTO);

        mockMvc.perform(put("/api/v1/vehicles/{uuid}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleDetailsDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid));
    }

    @Test
    void updateVehicleDetails_NotFound() throws Exception {
        when(vehicleService.updateVehicleDetails(eq(uuid), any(VehicleDetailsDTO.class)))
                .thenThrow(new NotFoundException("Vehicle not found"));

        mockMvc.perform(put("/api/v1/vehicles/{uuid}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleDetailsDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateVehicleInstructors_Success() throws Exception {
        List<String> instructorUuids = List.of(UUID.randomUUID().toString());
        when(vehicleService.updateVehicleInstructors(eq(uuid), any())).thenReturn(vehicleDetailsDTO);

        mockMvc.perform(patch("/api/v1/vehicles/{uuid}/instructors", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(instructorUuids)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteVehicle_Success() throws Exception {
        mockMvc.perform(delete("/api/v1/vehicles/{uuid}", uuid))
                .andExpect(status().isNoContent());
    }
}
