package com.felixherder.ftotbe.instructors;

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

@WebMvcTest(value = InstructorController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthFilter.class))
@Import({TestConfig.class, TestSecurityConfig.class})
class InstructorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private InstructorService instructorService;

    private InstructorDetailsDTO instructorDetailsDTO;
    private String uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID().toString();
        ProfileDTO profileDTO = new ProfileDTO(UUID.randomUUID().toString(), "John Doe", "123456789", "Some address", "http://example.com/image.jpg");
        instructorDetailsDTO = new InstructorDetailsDTO(uuid, profileDTO, Set.of(), Set.of(), Set.of());
    }

    @Test
    void getAll_ShouldReturnListOfInstructors() throws Exception {
        InstructorSummaryDTO summary = new InstructorSummaryDTO(uuid, instructorDetailsDTO.profile());

        when(instructorService.getAll()).thenReturn(List.of(summary));

        mockMvc.perform(get("/api/v1/instructors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value(uuid))
                .andExpect(jsonPath("$[0].profile.name").value("John Doe"));
    }

    @Test
    void getDetails_Success() throws Exception {
        when(instructorService.getByUuid(uuid)).thenReturn(instructorDetailsDTO);

        mockMvc.perform(get("/api/v1/instructors/{uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid))
                .andExpect(jsonPath("$.profile.name").value("John Doe"));
    }

    @Test
    void getDetails_NotFound() throws Exception {
        when(instructorService.getByUuid(uuid)).thenThrow(new NotFoundException("Instructor not found"));

        mockMvc.perform(get("/api/v1/instructors/{uuid}", uuid))
                .andExpect(status().isNotFound());
    }

    @Test
    void createInstructor_Success() throws Exception {
        when(instructorService.createInstructor(any(InstructorDetailsDTO.class))).thenReturn(instructorDetailsDTO);

        mockMvc.perform(post("/api/v1/instructors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(instructorDetailsDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid").value(uuid));
    }

    @Test
    void createInstructor_ValidationError() throws Exception {
        // Profile is required
        InstructorDetailsDTO invalidInstructor = new InstructorDetailsDTO(null, null, null, null, null);

        mockMvc.perform(post("/api/v1/instructors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidInstructor)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed!"))
                .andExpect(jsonPath("$.errors.profile").exists());
    }

    @Test
    void updateInstructorProfile_Success() throws Exception {
        when(instructorService.updateInstructorProfile(eq(uuid), any(InstructorDetailsDTO.class))).thenReturn(instructorDetailsDTO);

        mockMvc.perform(put("/api/v1/instructors/{uuid}/profile", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(instructorDetailsDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid));
    }

    @Test
    void updateInstructorVehicles_Success() throws Exception {
        List<String> vehicleUuids = List.of(UUID.randomUUID().toString());
        when(instructorService.updateInstructorVehicles(eq(uuid), any())).thenReturn(instructorDetailsDTO);

        mockMvc.perform(patch("/api/v1/instructors/{uuid}/vehicles", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleUuids)))
                .andExpect(status().isOk());
    }

    @Test
    void updateInstructorTrainees_Success() throws Exception {
        List<String> traineeUuids = List.of(UUID.randomUUID().toString());
        when(instructorService.updateInstructorTrainees(eq(uuid), any())).thenReturn(instructorDetailsDTO);

        mockMvc.perform(patch("/api/v1/instructors/{uuid}/trainees", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(traineeUuids)))
                .andExpect(status().isOk());
    }

    @Test
    void updateInstructorSessions_Success() throws Exception {
        List<String> sessionUuids = List.of(UUID.randomUUID().toString());
        when(instructorService.updateInstructorSessions(eq(uuid), any())).thenReturn(instructorDetailsDTO);

        mockMvc.perform(patch("/api/v1/instructors/{uuid}/sessions", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sessionUuids)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteSession_Success() throws Exception {
        mockMvc.perform(delete("/api/v1/instructors/{uuid}", uuid))
                .andExpect(status().isNoContent());
    }
}
