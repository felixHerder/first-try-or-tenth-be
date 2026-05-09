package com.felixherder.ftotbe.trainees;

import com.felixherder.ftotbe.exceptions.NotFoundException;
import com.felixherder.ftotbe.instructors.InstructorDO;
import com.felixherder.ftotbe.instructors.InstructorRepository;
import com.felixherder.ftotbe.profiles.ProfileDO;
import com.felixherder.ftotbe.profiles.ProfileDTO;
import com.felixherder.ftotbe.sessions.SessionDO;
import com.felixherder.ftotbe.sessions.SessionRepository;
import com.felixherder.ftotbe.vehicles.VehicleDO;
import com.felixherder.ftotbe.vehicles.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TraineeServiceImplTest {

    @Mock
    private TraineeRepository traineeRepository;
    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private InstructorRepository instructorRepository;
    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private TraineeMapper traineeMapper;

    @InjectMocks
    private TraineeServiceImpl traineeService;

    private TraineeDO traineeDO;
    private TraineeDetailsDTO traineeDetailsDTO;
    private String uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID().toString();
        ProfileDO profileDO = new ProfileDO();
        profileDO.setName("Trainee Name");

        traineeDO = new TraineeDO();
        traineeDO.setUuid(uuid);
        traineeDO.setProfile(profileDO);
        traineeDO.setSessions(new HashSet<>());

        ProfileDTO profileDTO = new ProfileDTO(UUID.randomUUID().toString(), "Trainee Name", "123456789", null, null);
        traineeDetailsDTO = new TraineeDetailsDTO(uuid, profileDTO, null, null, Set.of());
    }

    @Test
    void getAll_ShouldReturnListOfTraineeSummaryDTOs() {
        TraineeSummaryDTO summaryDTO = new TraineeSummaryDTO(uuid, traineeDetailsDTO.profile());

        when(traineeRepository.findAll()).thenReturn(List.of(traineeDO));
        when(traineeMapper.toSummaryDto(traineeDO)).thenReturn(summaryDTO);

        List<TraineeSummaryDTO> result = traineeService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(uuid, result.getFirst().uuid());
        verify(traineeRepository).findAll();
    }

    @Test
    void getByUuid_Success() {
        when(traineeRepository.findById(uuid)).thenReturn(Optional.of(traineeDO));
        when(traineeMapper.toDetailsDto(traineeDO)).thenReturn(traineeDetailsDTO);

        TraineeDetailsDTO result = traineeService.getByUuid(uuid);

        assertNotNull(result);
        assertEquals(uuid, result.uuid());
        verify(traineeRepository).findById(uuid);
    }

    @Test
    void getByUuid_NotFound_ShouldThrowException() {
        when(traineeRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> traineeService.getByUuid(uuid));
    }

    @Test
    void createTrainee_Success() {
        when(traineeMapper.toDO(traineeDetailsDTO)).thenReturn(traineeDO);
        when(traineeRepository.save(traineeDO)).thenReturn(traineeDO);
        when(traineeMapper.toDetailsDto(traineeDO)).thenReturn(traineeDetailsDTO);

        TraineeDetailsDTO result = traineeService.createTrainee(traineeDetailsDTO);

        assertNotNull(result);
        verify(traineeRepository).save(traineeDO);
    }

    @Test
    void updateTraineeProfile_Success() {
        when(traineeRepository.findById(uuid)).thenReturn(Optional.of(traineeDO));
        when(traineeRepository.save(traineeDO)).thenReturn(traineeDO);
        when(traineeMapper.toDetailsDto(traineeDO)).thenReturn(traineeDetailsDTO);

        TraineeDetailsDTO result = traineeService.updateTraineeProfile(uuid, traineeDetailsDTO);

        assertNotNull(result);
        verify(traineeMapper).updateDoFromDto(traineeDetailsDTO, traineeDO);
        verify(traineeRepository).save(traineeDO);
    }

    @Test
    void updateTraineeVehicle_Success() {
        String vehicleUuid = UUID.randomUUID().toString();
        VehicleDO vehicleDO = new VehicleDO();

        when(traineeRepository.findById(uuid)).thenReturn(Optional.of(traineeDO));
        when(vehicleRepository.findById(vehicleUuid)).thenReturn(Optional.of(vehicleDO));
        when(traineeRepository.save(traineeDO)).thenReturn(traineeDO);
        when(traineeMapper.toDetailsDto(traineeDO)).thenReturn(traineeDetailsDTO);

        TraineeDetailsDTO result = traineeService.updateTraineeVehicle(uuid, vehicleUuid);

        assertNotNull(result);
        verify(traineeRepository).save(traineeDO);
    }

    @Test
    void updateTraineeInstructor_Success() {
        String instructorUuid = UUID.randomUUID().toString();
        InstructorDO instructorDO = new InstructorDO();

        when(traineeRepository.findById(uuid)).thenReturn(Optional.of(traineeDO));
        when(instructorRepository.findById(instructorUuid)).thenReturn(Optional.of(instructorDO));
        when(traineeRepository.save(traineeDO)).thenReturn(traineeDO);
        when(traineeMapper.toDetailsDto(traineeDO)).thenReturn(traineeDetailsDTO);

        TraineeDetailsDTO result = traineeService.updateTraineeInstructor(uuid, instructorUuid);

        assertNotNull(result);
        verify(traineeRepository).save(traineeDO);
    }

    @Test
    void updateTraineeSessions_Success() {
        String sessionUuid = UUID.randomUUID().toString();
        SessionDO sessionDO = new SessionDO();

        when(traineeRepository.findById(uuid)).thenReturn(Optional.of(traineeDO));
        when(sessionRepository.findAllById(any())).thenReturn(List.of(sessionDO));
        when(traineeRepository.save(traineeDO)).thenReturn(traineeDO);
        when(traineeMapper.toDetailsDto(traineeDO)).thenReturn(traineeDetailsDTO);

        TraineeDetailsDTO result = traineeService.updateTraineeSessions(uuid, List.of(sessionUuid));

        assertNotNull(result);
        verify(traineeRepository).save(traineeDO);
    }

    @Test
    void deleteSession_Success() {
        doNothing().when(traineeRepository).deleteById(uuid);

        traineeService.deleteSession(uuid);

        verify(traineeRepository).deleteById(uuid);
    }
}
