package com.felixherder.ftotbe.trainees;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felixherder.ftotbe.auth.JwtAuthFilter;
import com.felixherder.ftotbe.config.TestConfig;
import com.felixherder.ftotbe.config.TestSecurityConfig;
import com.felixherder.ftotbe.exceptions.NotFoundException;
import com.felixherder.ftotbe.profiles.ProfileDTO;
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

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = TraineeController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthFilter.class))
@Import({TestConfig.class, TestSecurityConfig.class})
class TraineeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TraineeService traineeService;

    private TraineeDetailsDTO traineeDetailsDTO;
    private String uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID().toString();
        ProfileDTO profileDTO = new ProfileDTO(UUID.randomUUID().toString(), "Trainee Name", "123456789", "Some address", "http://example.com/image.jpg");
        traineeDetailsDTO = new TraineeDetailsDTO(uuid, profileDTO, null, null, Set.of());
    }

    @Test
    void getAll_ShouldReturnListOfTrainees() throws Exception {
        TraineeSummaryDTO summary = new TraineeSummaryDTO(uuid, traineeDetailsDTO.profile());
        when(traineeService.getAll()).thenReturn(List.of(summary));

        mockMvc.perform(get("/api/v1/trainees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value(uuid));
    }

    @Test
    void getDetails_Success() throws Exception {
        when(traineeService.getByUuid(uuid)).thenReturn(traineeDetailsDTO);

        mockMvc.perform(get("/api/v1/trainees/{uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid));
    }

    @Test
    void getDetails_NotFound() throws Exception {
        when(traineeService.getByUuid(uuid)).thenThrow(new NotFoundException("Trainee not found"));

        mockMvc.perform(get("/api/v1/trainees/{uuid}", uuid))
                .andExpect(status().isNotFound());
    }

    @Test
    void createTrainee_Success() throws Exception {
        when(traineeService.createTrainee(any(TraineeDetailsDTO.class))).thenReturn(traineeDetailsDTO);

        mockMvc.perform(post("/api/v1/trainees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(traineeDetailsDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid").value(uuid));
    }

    @Test
    void updateTraineeProfile_Success() throws Exception {
        when(traineeService.updateTraineeProfile(eq(uuid), any(TraineeDetailsDTO.class))).thenReturn(traineeDetailsDTO);

        mockMvc.perform(put("/api/v1/trainees/{uuid}/profile", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(traineeDetailsDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid));
    }

    @Test
    void updateTraineeVehicle_Success() throws Exception {
        String vehicleUuid = UUID.randomUUID().toString();
        when(traineeService.updateTraineeVehicle(eq(uuid), any())).thenReturn(traineeDetailsDTO);

        mockMvc.perform(patch("/api/v1/trainees/{uuid}/vehicle", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleUuid))
                .andExpect(status().isOk());
    }

    @Test
    void updateTraineeInstructor_Success() throws Exception {
        String instructorUuid = UUID.randomUUID().toString();
        when(traineeService.updateTraineeInstructor(eq(uuid), any())).thenReturn(traineeDetailsDTO);

        mockMvc.perform(patch("/api/v1/trainees/{uuid}/instructor", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(instructorUuid))
                .andExpect(status().isOk());
    }

    @Test
    void updateTraineeSessions_Success() throws Exception {
        List<String> sessionUuids = List.of(UUID.randomUUID().toString());
        when(traineeService.updateTraineeSessions(eq(uuid), any())).thenReturn(traineeDetailsDTO);

        mockMvc.perform(patch("/api/v1/trainees/{uuid}/sessions", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sessionUuids)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTrainee_Success() throws Exception {
        mockMvc.perform(delete("/api/v1/trainees/{uuid}", uuid))
                .andExpect(status().isNoContent());
    }
}
