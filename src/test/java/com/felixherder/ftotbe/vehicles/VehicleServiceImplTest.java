package com.felixherder.ftotbe.vehicles;

import com.felixherder.ftotbe.exceptions.NotFoundException;
import com.felixherder.ftotbe.instructors.InstructorDO;
import com.felixherder.ftotbe.instructors.InstructorRepository;
import com.felixherder.ftotbe.sessions.SessionDO;
import com.felixherder.ftotbe.sessions.SessionRepository;
import com.felixherder.ftotbe.trainees.TraineeDO;
import com.felixherder.ftotbe.trainees.TraineeRepository;
import com.felixherder.ftotbe.vehicles.enums.EngineType;
import com.felixherder.ftotbe.vehicles.enums.FuelType;
import com.felixherder.ftotbe.vehicles.enums.TransmissionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {

    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private InstructorRepository instructorRepository;
    @Mock
    private TraineeRepository traineeRepository;
    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private VehicleMapper vehicleMapper;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    private VehicleDO vehicleDO;
    private VehicleDetailsDTO vehicleDetailsDTO;
    private String uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID().toString();
        vehicleDO = new VehicleDO();
        vehicleDO.setUuid(uuid);
        vehicleDO.setModel("Model S");
        vehicleDO.setMake("Tesla");
        vehicleDO.setYear(Year.of(2022));

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
                .build();
    }

    @Test
    void getAll_ShouldReturnListOfVehicleSummaryDTOs() {
        VehicleSummaryDTO summaryDTO = VehicleSummaryDTO.builder()
                .uuid(uuid)
                .model("Model S")
                .make("Tesla")
                .build();

        when(vehicleRepository.findAll()).thenReturn(List.of(vehicleDO));
        when(vehicleMapper.toSummaryDto(vehicleDO)).thenReturn(summaryDTO);

        List<VehicleSummaryDTO> result = vehicleService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(uuid, result.getFirst().uuid());
        verify(vehicleRepository).findAll();
        verify(vehicleMapper).toSummaryDto(vehicleDO);
    }

    @Test
    void getByUuid_Success() {
        when(vehicleRepository.findById(uuid)).thenReturn(Optional.of(vehicleDO));
        when(vehicleMapper.toDetailsDto(vehicleDO)).thenReturn(vehicleDetailsDTO);

        VehicleDetailsDTO result = vehicleService.getByUuid(uuid);

        assertNotNull(result);
        assertEquals(uuid, result.uuid());
        verify(vehicleRepository).findById(uuid);
    }

    @Test
    void getByUuid_NotFound_ShouldThrowException() {
        when(vehicleRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> vehicleService.getByUuid(uuid));
        verify(vehicleRepository).findById(uuid);
    }

    @Test
    void createVehicle_Success() {
        when(vehicleMapper.toDO(vehicleDetailsDTO)).thenReturn(vehicleDO);
        when(vehicleRepository.save(vehicleDO)).thenReturn(vehicleDO);
        when(vehicleMapper.toDetailsDto(vehicleDO)).thenReturn(vehicleDetailsDTO);

        VehicleDetailsDTO result = vehicleService.createVehicle(vehicleDetailsDTO);

        assertNotNull(result);
        assertEquals(uuid, result.uuid());
        verify(vehicleRepository).save(vehicleDO);
    }

    @Test
    void updateVehicleDetails_Success() {
        when(vehicleRepository.findById(uuid)).thenReturn(Optional.of(vehicleDO));
        when(vehicleRepository.save(vehicleDO)).thenReturn(vehicleDO);
        when(vehicleMapper.toDetailsDto(vehicleDO)).thenReturn(vehicleDetailsDTO);

        VehicleDetailsDTO result = vehicleService.updateVehicleDetails(uuid, vehicleDetailsDTO);

        assertNotNull(result);
        assertEquals(uuid, result.uuid());
        verify(vehicleMapper).updateDoFromDto(vehicleDetailsDTO, vehicleDO);
        verify(vehicleRepository).save(vehicleDO);
    }

    @Test
    void updateVehicleDetails_NotFound_ShouldThrowException() {
        when(vehicleRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> vehicleService.updateVehicleDetails(uuid, vehicleDetailsDTO));
    }

    @Test
    void updateVehicleInstructors_Success() {
        String instructorUuid = UUID.randomUUID().toString();
        InstructorDO instructorDO = new InstructorDO();
        instructorDO.setUuid(instructorUuid);
        instructorDO.setVehicles(new java.util.HashSet<>());

        when(vehicleRepository.findById(uuid)).thenReturn(Optional.of(vehicleDO));
        when(instructorRepository.findAllById(any())).thenReturn(List.of(instructorDO));
        when(vehicleRepository.save(vehicleDO)).thenReturn(vehicleDO);
        when(vehicleMapper.toDetailsDto(vehicleDO)).thenReturn(vehicleDetailsDTO);

        VehicleDetailsDTO result = vehicleService.updateVehicleInstructors(uuid, List.of(instructorUuid));

        assertNotNull(result);
        verify(instructorRepository).findAllById(any());
        verify(vehicleRepository).save(vehicleDO);
    }

    @Test
    void updateVehicleTrainees_Success() {
        String traineeUuid = UUID.randomUUID().toString();
        TraineeDO traineeDO = new TraineeDO();
        traineeDO.setUuid(traineeUuid);

        when(vehicleRepository.findById(uuid)).thenReturn(Optional.of(vehicleDO));
        when(traineeRepository.findAllById(any())).thenReturn(List.of(traineeDO));
        when(vehicleRepository.save(vehicleDO)).thenReturn(vehicleDO);
        when(vehicleMapper.toDetailsDto(vehicleDO)).thenReturn(vehicleDetailsDTO);

        VehicleDetailsDTO result = vehicleService.updateVehicleTrainees(uuid, List.of(traineeUuid));

        assertNotNull(result);
        verify(traineeRepository).findAllById(any());
        verify(vehicleRepository).save(vehicleDO);
    }

    @Test
    void updateVehicleSessions_Success() {
        String sessionUuid = UUID.randomUUID().toString();
        SessionDO sessionDO = new SessionDO();
        sessionDO.setUuid(sessionUuid);

        when(vehicleRepository.findById(uuid)).thenReturn(Optional.of(vehicleDO));
        when(sessionRepository.findAllById(any())).thenReturn(List.of(sessionDO));
        when(vehicleRepository.save(vehicleDO)).thenReturn(vehicleDO);
        when(vehicleMapper.toDetailsDto(vehicleDO)).thenReturn(vehicleDetailsDTO);

        VehicleDetailsDTO result = vehicleService.updateVehicleSessions(uuid, List.of(sessionUuid));

        assertNotNull(result);
        verify(sessionRepository).findAllById(any());
        verify(vehicleRepository).save(vehicleDO);
    }

    @Test
    void deleteVehicle_Success() {
        doNothing().when(vehicleRepository).deleteById(uuid);

        vehicleService.deleteVehicle(uuid);

        verify(vehicleRepository).deleteById(uuid);
    }
}
