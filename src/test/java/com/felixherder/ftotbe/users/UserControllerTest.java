package com.felixherder.ftotbe.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felixherder.ftotbe.auth.AuthRequest;
import com.felixherder.ftotbe.auth.JwtAuthFilter;
import com.felixherder.ftotbe.auth.JwtService;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthFilter.class))
@Import({TestConfig.class, TestSecurityConfig.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private AuthenticationManager authenticationManager;

    private UserDetailsDTO userDetailsDTO;
    private String uuid;
    private final String username = "testuser";

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID().toString();
        ProfileDTO profileDTO = new ProfileDTO(UUID.randomUUID().toString(), "Test User", "123456789", "Some address", "http://example.com/image.jpg");
        userDetailsDTO = new UserDetailsDTO(uuid, profileDTO, username, "ROLE_ADMIN");
    }

    @Test
    void getAll_ShouldReturnListOfUsers() throws Exception {
        when(userService.getAll()).thenReturn(List.of(userDetailsDTO));

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value(uuid))
                .andExpect(jsonPath("$[0].username").value(username));
    }

    @Test
    void getByUuid_Success() throws Exception {
        when(userService.getByUuid(uuid)).thenReturn(userDetailsDTO);

        mockMvc.perform(get("/api/v1/users/{uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid))
                .andExpect(jsonPath("$.username").value(username));
    }

    @Test
    void getByUuid_NotFound() throws Exception {
        when(userService.getByUuid(uuid)).thenThrow(new NotFoundException("User not found"));

        mockMvc.perform(get("/api/v1/users/{uuid}", uuid))
                .andExpect(status().isNotFound());
    }

    @Test
    void loginUser_Success() throws Exception {
        AuthRequest authRequest = new AuthRequest(username, "password123");
        UserDO userDO = new UserDO();
        userDO.setUsername(username);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userService.loadUserByUsername(username)).thenReturn(userDO);
        when(jwtService.generateToken(userDO)).thenReturn("jwtToken");
        when(userService.toDetailsDTO(userDO)).thenReturn(userDetailsDTO);

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwtToken"))
                .andExpect(jsonPath("$.userDetails.username").value(username));
    }

    @Test
    void registerUser_Success() throws Exception {
        UserRegisterDTO registerDTO = new UserRegisterDTO(userDetailsDTO.profile(), username, "password123", "ROLE_ADMIN");
        when(userService.registerUser(any(UserRegisterDTO.class))).thenReturn(userDetailsDTO);

        mockMvc.perform(post("/api/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid").value(uuid));
    }

    @Test
    void editUser_Success() throws Exception {
        UserEditDTO editDTO = new UserEditDTO(userDetailsDTO.profile(), username, "newPassword123", "ROLE_ADMIN");
        when(userService.editUser(eq(uuid), any(UserEditDTO.class))).thenReturn(userDetailsDTO);

        mockMvc.perform(patch("/api/v1/users/{uuid}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid));
    }

    @Test
    void removeUser_Success() throws Exception {
        mockMvc.perform(delete("/api/v1/users/{uuid}", uuid))
                .andExpect(status().isNoContent());
    }
}
