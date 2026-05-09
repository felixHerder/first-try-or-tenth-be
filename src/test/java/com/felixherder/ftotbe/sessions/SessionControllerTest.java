package com.felixherder.ftotbe.sessions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felixherder.ftotbe.auth.JwtAuthFilter;
import com.felixherder.ftotbe.config.TestConfig;
import com.felixherder.ftotbe.config.TestSecurityConfig;
import com.felixherder.ftotbe.exceptions.NotFoundException;
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

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = SessionController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthFilter.class))
@Import({TestConfig.class, TestSecurityConfig.class})
class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SessionService sessionService;

    private SessionDetailsDTO sessionDetailsDTO;
    private String uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID().toString();
        sessionDetailsDTO = new SessionDetailsDTO(uuid, ZonedDateTime.now(), null, null, null);
    }

    @Test
    void getAll_ShouldReturnListOfSessions() throws Exception {
        SessionSummaryDTO summary = new SessionSummaryDTO(uuid, sessionDetailsDTO.scheduledAt(), "Trainee", "Instructor", "Vehicle");
        when(sessionService.getAll()).thenReturn(List.of(summary));

        mockMvc.perform(get("/api/v1/sessions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value(uuid));
    }

    @Test
    void getDetails_Success() throws Exception {
        when(sessionService.getByUuid(uuid)).thenReturn(sessionDetailsDTO);

        mockMvc.perform(get("/api/v1/sessions/{uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid));
    }

    @Test
    void getDetails_NotFound() throws Exception {
        when(sessionService.getByUuid(uuid)).thenThrow(new NotFoundException("Session not found"));

        mockMvc.perform(get("/api/v1/sessions/{uuid}", uuid))
                .andExpect(status().isNotFound());
    }

    @Test
    void createSession_Success() throws Exception {
        SessionCreateDTO createDTO = new SessionCreateDTO(ZonedDateTime.now().plusDays(1), "trainee", "instructor", "vehicle");
        when(sessionService.createSession(any(SessionCreateDTO.class))).thenReturn(sessionDetailsDTO);

        mockMvc.perform(post("/api/v1/sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid").value(uuid));
    }

    @Test
    void editSession_Success() throws Exception {
        SessionEditDTO editDTO = new SessionEditDTO(ZonedDateTime.now().plusDays(1), null, null, null);
        when(sessionService.editSession(eq(uuid), any(SessionEditDTO.class))).thenReturn(sessionDetailsDTO);

        mockMvc.perform(patch("/api/v1/sessions/{uuid}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid));
    }

    @Test
    void deleteSession_Success() throws Exception {
        mockMvc.perform(delete("/api/v1/sessions/{uuid}", uuid))
                .andExpect(status().isNoContent());
    }
}
