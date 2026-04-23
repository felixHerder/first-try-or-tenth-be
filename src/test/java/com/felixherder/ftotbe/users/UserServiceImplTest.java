package com.felixherder.ftotbe.users;

import com.felixherder.ftotbe.exceptions.NotFoundException;
import com.felixherder.ftotbe.exceptions.UsernameConflictException;
import com.felixherder.ftotbe.profiles.ProfileDO;
import com.felixherder.ftotbe.profiles.ProfileDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDO userDO;
    private UserDetailsDTO userDetailsDTO;
    private String uuid;
    private final String username = "testuser";

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID().toString();
        ProfileDO profileDO = new ProfileDO();
        profileDO.setName("Test User");

        userDO = new UserDO();
        userDO.setUuid(uuid);
        userDO.setUsername(username);
        userDO.setPassword("encodedPassword");
        userDO.setProfile(profileDO);

        ProfileDTO profileDTO = new ProfileDTO(UUID.randomUUID().toString(), "Test User", "123456789", null, null);
        userDetailsDTO = new UserDetailsDTO(uuid, profileDTO, username, "ROLE_ADMIN");
    }

    @Test
    void getAll_ShouldReturnListOfUserDetailsDTOs() {
        when(userRepository.findAll()).thenReturn(List.of(userDO));
        when(userMapper.toDetailsDTO(userDO)).thenReturn(userDetailsDTO);

        List<UserDetailsDTO> result = userService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(uuid, result.getFirst().uuid());
        verify(userRepository).findAll();
    }

    @Test
    void getByUuid_Success() {
        when(userRepository.findById(uuid)).thenReturn(Optional.of(userDO));
        when(userMapper.toDetailsDTO(userDO)).thenReturn(userDetailsDTO);

        UserDetailsDTO result = userService.getByUuid(uuid);

        assertNotNull(result);
        assertEquals(uuid, result.uuid());
        verify(userRepository).findById(uuid);
    }

    @Test
    void getByUuid_NotFound_ShouldThrowException() {
        when(userRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getByUuid(uuid));
    }

    @Test
    void loadUserByUsername_Success() {
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(userDO));

        UserDetails result = userService.loadUserByUsername(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(userRepository).findByUsername(username);
    }

    @Test
    void loadUserByUsername_NotFound_ShouldThrowException() {
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.loadUserByUsername(username));
    }

    @Test
    void toDetailsDTO_Success() {
        when(userMapper.toDetailsDTO(userDO)).thenReturn(userDetailsDTO);

        UserDetailsDTO result = userService.toDetailsDTO(userDO);

        assertNotNull(result);
        assertEquals(uuid, result.uuid());
        verify(userMapper).toDetailsDTO(userDO);
    }

    @Test
    void registerUser_Success() {
        UserRegisterDTO registerDTO = new UserRegisterDTO(null, username, "password123", null);

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(userMapper.toDO(registerDTO)).thenReturn(userDO);
        when(passwordEncoder.encode("encodedPassword")).thenReturn("encodedPassword"); // In UserServiceImpl, it encodes userDO.getPassword()
        // Wait, userDO.getPassword() is already set in setUp. 
        // In registerUser: userDO.setPassword(passwordEncoder.encode(userDO.getPassword()));

        when(userRepository.save(userDO)).thenReturn(userDO);
        when(userMapper.toDetailsDTO(userDO)).thenReturn(userDetailsDTO);

        UserDetailsDTO result = userService.registerUser(registerDTO);

        assertNotNull(result);
        verify(userRepository).save(userDO);
        verify(passwordEncoder).encode(any());
    }

    @Test
    void registerUser_UsernameConflict_ShouldThrowException() {
        UserRegisterDTO registerDTO = new UserRegisterDTO(null, username, "password123", null);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(userDO));

        assertThrows(UsernameConflictException.class, () -> userService.registerUser(registerDTO));
    }

    @Test
    void editUser_Success() {
        UserEditDTO editDTO = new UserEditDTO(null, username, "newPassword", null);

        when(userRepository.findById(uuid)).thenReturn(Optional.of(userDO));
        when(passwordEncoder.encode(any())).thenReturn("newEncodedPassword");
        when(userRepository.save(userDO)).thenReturn(userDO);
        when(userMapper.toDetailsDTO(userDO)).thenReturn(userDetailsDTO);

        UserDetailsDTO result = userService.editUser(uuid, editDTO);

        assertNotNull(result);
        verify(userMapper).updateDoFromDto(editDTO, userDO);
        verify(userRepository).save(userDO);
    }

    @Test
    void editUser_NotFound_ShouldThrowException() {
        UserEditDTO editDTO = new UserEditDTO(null, username, "newPassword", null);
        when(userRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.editUser(uuid, editDTO));
    }

    @Test
    void removeUser_Success() {
        doNothing().when(userRepository).deleteById(uuid);

        userService.removeUser(uuid);

        verify(userRepository).deleteById(uuid);
    }
}
