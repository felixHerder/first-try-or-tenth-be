package com.felixherder.ftotbe.sessions;

import com.felixherder.ftotbe.exceptions.NotFoundException;
import com.felixherder.ftotbe.instructors.InstructorDO;
import com.felixherder.ftotbe.instructors.InstructorRepository;
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

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionServiceImplTest {

    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private InstructorRepository instructorRepository;
    @Mock
    private TraineeRepository traineeRepository;
    @Mock
    private SessionMapper sessionMapper;

    @InjectMocks
    private SessionServiceImpl sessionService;

    private SessionDO sessionDO;
    private SessionDetailsDTO sessionDetailsDTO;
    private String uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID().toString();
        sessionDO = new SessionDO();
        sessionDO.setUuid(uuid);
        sessionDO.setScheduledAt(ZonedDateTime.now());

        sessionDetailsDTO = new SessionDetailsDTO(uuid, sessionDO.getScheduledAt(), null, null, null);
    }

    @Test
    void getAll_ShouldReturnListOfSessionSummaryDTOs() {
        SessionSummaryDTO summaryDTO = new SessionSummaryDTO(uuid, sessionDO.getScheduledAt(), "Trainee", "Instructor", "Vehicle");

        when(sessionRepository.findAll()).thenReturn(List.of(sessionDO));
        when(sessionMapper.toSummaryDto(sessionDO)).thenReturn(summaryDTO);

        List<SessionSummaryDTO> result = sessionService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(uuid, result.getFirst().uuid());
        verify(sessionRepository).findAll();
    }

    @Test
    void getByUuid_Success() {
        when(sessionRepository.findById(uuid)).thenReturn(Optional.of(sessionDO));
        when(sessionMapper.toDetailsDto(sessionDO)).thenReturn(sessionDetailsDTO);

        SessionDetailsDTO result = sessionService.getByUuid(uuid);

        assertNotNull(result);
        assertEquals(uuid, result.uuid());
        verify(sessionRepository).findById(uuid);
    }

    @Test
    void getByUuid_NotFound_ShouldThrowException() {
        when(sessionRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> sessionService.getByUuid(uuid));
    }

    @Test
    void createSession_Success() {
        String vehicleUuid = UUID.randomUUID().toString();
        String instructorUuid = UUID.randomUUID().toString();
        String traineeUuid = UUID.randomUUID().toString();
        SessionCreateDTO createDTO = new SessionCreateDTO(ZonedDateTime.now(), traineeUuid, instructorUuid, vehicleUuid);

        when(sessionMapper.toDO(createDTO)).thenReturn(sessionDO);
        when(vehicleRepository.findById(vehicleUuid)).thenReturn(Optional.of(new VehicleDO()));
        when(instructorRepository.findById(instructorUuid)).thenReturn(Optional.of(new InstructorDO()));
        when(traineeRepository.findById(traineeUuid)).thenReturn(Optional.of(new TraineeDO()));
        when(sessionRepository.save(sessionDO)).thenReturn(sessionDO);
        when(sessionMapper.toDetailsDto(sessionDO)).thenReturn(sessionDetailsDTO);

        SessionDetailsDTO result = sessionService.createSession(createDTO);

        assertNotNull(result);
        verify(sessionRepository).save(sessionDO);
        verify(vehicleRepository).findById(vehicleUuid);
        verify(instructorRepository).findById(instructorUuid);
        verify(traineeRepository).findById(traineeUuid);
    }

    @Test
    void createSession_VehicleNotFound_ShouldThrowException() {
        String vehicleUuid = UUID.randomUUID().toString();
        SessionCreateDTO createDTO = new SessionCreateDTO(ZonedDateTime.now(), "trainee", "instructor", vehicleUuid);

        when(sessionMapper.toDO(createDTO)).thenReturn(sessionDO);
        when(vehicleRepository.findById(vehicleUuid)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> sessionService.createSession(createDTO));
    }

    @Test
    void editSession_Success() {
        String vehicleUuid = UUID.randomUUID().toString();
        SessionEditDTO editDTO = new SessionEditDTO(ZonedDateTime.now(), null, null, vehicleUuid);

        when(sessionRepository.findById(uuid)).thenReturn(Optional.of(sessionDO));
        when(vehicleRepository.findById(vehicleUuid)).thenReturn(Optional.of(new VehicleDO()));
        when(sessionRepository.save(sessionDO)).thenReturn(sessionDO);
        when(sessionMapper.toDetailsDto(sessionDO)).thenReturn(sessionDetailsDTO);

        SessionDetailsDTO result = sessionService.editSession(uuid, editDTO);

        assertNotNull(result);
        verify(sessionMapper).updateDoFromDto(editDTO, sessionDO);
        verify(sessionRepository).save(sessionDO);
    }

    @Test
    void editSession_NotFound_ShouldThrowException() {
        SessionEditDTO editDTO = new SessionEditDTO(ZonedDateTime.now(), null, null, null);
        when(sessionRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> sessionService.editSession(uuid, editDTO));
    }

    @Test
    void deleteSession_Success() {
        doNothing().when(sessionRepository).deleteById(uuid);

        sessionService.deleteSession(uuid);

        verify(sessionRepository).deleteById(uuid);
    }
}
