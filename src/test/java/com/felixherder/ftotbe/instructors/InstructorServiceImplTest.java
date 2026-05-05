package com.felixherder.ftotbe.instructors;

import com.felixherder.ftotbe.exceptions.NotFoundException;
import com.felixherder.ftotbe.profiles.ProfileDO;
import com.felixherder.ftotbe.profiles.ProfileDTO;
import com.felixherder.ftotbe.sessions.SessionDO;
import com.felixherder.ftotbe.sessions.SessionRepository;
import com.felixherder.ftotbe.trainees.TraineeDO;
import com.felixherder.ftotbe.trainees.TraineeRepository;
import com.felixherder.ftotbe.vehicles.VehicleDO;
import com.felixherder.ftotbe.vehicles.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorServiceImplTest {

    @Mock
    private InstructorRepository instructorRepository;
    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private TraineeRepository traineeRepository;
    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private InstructorMapper instructorMapper;

    @InjectMocks
    private InstructorServiceImpl instructorService;

    private InstructorDO instructorDO;
    private InstructorDetailsDTO instructorDetailsDTO;
    private String uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID().toString();
        ProfileDO profileDO = new ProfileDO();
        profileDO.setName("John Doe");
        profileDO.setPhoneNumber("123456789");

        instructorDO = new InstructorDO();
        instructorDO.setUuid(uuid);
        instructorDO.setProfile(profileDO);

        ProfileDTO profileDTO = new ProfileDTO(UUID.randomUUID().toString(), "John Doe", "123456789", null, null);
        instructorDetailsDTO = new InstructorDetailsDTO(uuid, profileDTO, Set.of(), Set.of(), Set.of());
    }

    @Test
    void getAll_ShouldReturnListOfInstructorSummaryDTOs() {
        InstructorSummaryDTO summaryDTO = new InstructorSummaryDTO(uuid, instructorDetailsDTO.profile());

        when(instructorRepository.findAll()).thenReturn(List.of(instructorDO));
        when(instructorMapper.toSummaryDto(instructorDO)).thenReturn(summaryDTO);

        List<InstructorSummaryDTO> result = instructorService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(uuid, result.getFirst().uuid());
        verify(instructorRepository).findAll();
    }

    @Test
    void getByUuid_Success() {
        when(instructorRepository.findById(uuid)).thenReturn(Optional.of(instructorDO));
        when(instructorMapper.toDetailsDto(instructorDO)).thenReturn(instructorDetailsDTO);

        InstructorDetailsDTO result = instructorService.getByUuid(uuid);

        assertNotNull(result);
        assertEquals(uuid, result.uuid());
        verify(instructorRepository).findById(uuid);
    }

    @Test
    void getByUuid_NotFound_ShouldThrowException() {
        when(instructorRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> instructorService.getByUuid(uuid));
    }

    @Test
    void createInstructor_Success() {
        when(instructorMapper.toDO(instructorDetailsDTO)).thenReturn(instructorDO);
        when(instructorRepository.save(instructorDO)).thenReturn(instructorDO);
        when(instructorMapper.toDetailsDto(instructorDO)).thenReturn(instructorDetailsDTO);

        InstructorDetailsDTO result = instructorService.createInstructor(instructorDetailsDTO);

        assertNotNull(result);
        assertEquals(uuid, result.uuid());
        verify(instructorRepository).save(instructorDO);
    }

    @Test
    void updateInstructorProfile_Success() {
        when(instructorRepository.findById(uuid)).thenReturn(Optional.of(instructorDO));
        when(instructorRepository.save(instructorDO)).thenReturn(instructorDO);
        when(instructorMapper.toDetailsDto(instructorDO)).thenReturn(instructorDetailsDTO);

        InstructorDetailsDTO result = instructorService.updateInstructorProfile(uuid, instructorDetailsDTO.profile());

        assertNotNull(result);
        assertEquals(uuid, result.uuid());
        verify(instructorMapper).updateDoFromProfileDto(instructorDetailsDTO.profile(), instructorDO);
        verify(instructorRepository).save(instructorDO);
    }

    @Test
    void updateInstructorVehicles_Success() {
        String vehicleUuid = UUID.randomUUID().toString();
        VehicleDO vehicleDO = new VehicleDO();
        vehicleDO.setUuid(vehicleUuid);
        vehicleDO.setInstructors(new java.util.HashSet<>());

        when(instructorRepository.findById(uuid)).thenReturn(Optional.of(instructorDO));
        when(vehicleRepository.findAllById(any())).thenReturn(List.of(vehicleDO));
        when(instructorRepository.save(instructorDO)).thenReturn(instructorDO);
        when(instructorMapper.toDetailsDto(instructorDO)).thenReturn(instructorDetailsDTO);

        InstructorDetailsDTO result = instructorService.updateInstructorVehicles(uuid, List.of(vehicleUuid));

        assertNotNull(result);
        verify(vehicleRepository).findAllById(any());
        verify(instructorRepository).save(instructorDO);
    }

    @Test
    void updateInstructorTrainees_Success() {
        String traineeUuid = UUID.randomUUID().toString();
        TraineeDO traineeDO = new TraineeDO();
        traineeDO.setUuid(traineeUuid);

        when(instructorRepository.findById(uuid)).thenReturn(Optional.of(instructorDO));
        when(traineeRepository.findAllById(any())).thenReturn(List.of(traineeDO));
        when(instructorRepository.save(instructorDO)).thenReturn(instructorDO);
        when(instructorMapper.toDetailsDto(instructorDO)).thenReturn(instructorDetailsDTO);

        InstructorDetailsDTO result = instructorService.updateInstructorTrainees(uuid, List.of(traineeUuid));

        assertNotNull(result);
        verify(traineeRepository).findAllById(any());
        verify(instructorRepository).save(instructorDO);
    }

    @Test
    void updateInstructorSessions_Success() {
        String sessionUuid = UUID.randomUUID().toString();
        SessionDO sessionDO = new SessionDO();
        sessionDO.setUuid(sessionUuid);

        when(instructorRepository.findById(uuid)).thenReturn(Optional.of(instructorDO));
        when(sessionRepository.findAllById(any())).thenReturn(List.of(sessionDO));
        when(instructorRepository.save(instructorDO)).thenReturn(instructorDO);
        when(instructorMapper.toDetailsDto(instructorDO)).thenReturn(instructorDetailsDTO);

        InstructorDetailsDTO result = instructorService.updateInstructorSessions(uuid, List.of(sessionUuid));

        assertNotNull(result);
        verify(sessionRepository).findAllById(any());
        verify(instructorRepository).save(instructorDO);
    }

    @Test
    void deleteInstructor_Success() {
        doNothing().when(instructorRepository).deleteById(uuid);

        instructorService.deleteInstructor(uuid);

        verify(instructorRepository).deleteById(uuid);
    }
}
