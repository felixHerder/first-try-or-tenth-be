package com.felixherder.ftotbe.sessions;

import com.felixherder.ftotbe.exceptions.NotFoundException;
import com.felixherder.ftotbe.instructors.InstructorRepository;
import com.felixherder.ftotbe.trainees.TraineeRepository;
import com.felixherder.ftotbe.vehicles.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final VehicleRepository vehicleRepository;
    private final InstructorRepository instructorRepository;
    private final TraineeRepository traineeRepository;
    private final SessionMapper sessionMapper;

    public SessionServiceImpl(SessionRepository sessionRepository,
                              VehicleRepository vehicleRepository,
                              InstructorRepository instructorRepository,
                              TraineeRepository traineeRepository,
                              SessionMapper sessionMapper) {
        this.sessionRepository = sessionRepository;
        this.vehicleRepository = vehicleRepository;
        this.instructorRepository = instructorRepository;
        this.traineeRepository = traineeRepository;
        this.sessionMapper = sessionMapper;
    }

    @Override
    public List<SessionSummaryDTO> getAll() {
        var sessions = sessionRepository.findAll();
        return sessions.stream().map(sessionMapper::toSummaryDto).toList();
    }

    @Override
    public SessionDetailsDTO getByUuid(String uuid) {
        return sessionRepository.findById(uuid)
                .map(sessionMapper::toDetailsDto)
                .orElseThrow(() -> new NotFoundException("Session with uuid: " + uuid + " not found!"));
    }

    @Override
    public SessionDetailsDTO createSession(SessionCreateDTO sessionCreateDTO) {
        var sessionDO = sessionMapper.toDO(sessionCreateDTO);
        findSetObjectsByUuid(sessionDO, sessionCreateDTO.vehicleUuid(), sessionCreateDTO.instructorUuid(), sessionCreateDTO.traineeUuid());
        var savedSessionDO = sessionRepository.save(sessionDO);
        return sessionMapper.toDetailsDto(savedSessionDO);
    }


    @Override
    public SessionDetailsDTO editSession(String uuid, SessionEditDTO sessionEditDTO) {
        return sessionRepository.findById(uuid)
                .map(sessionDO -> {
                    sessionMapper.updateDoFromDto(sessionEditDTO, sessionDO);
                    findSetObjectsByUuid(sessionDO, sessionEditDTO.vehicleUuid(), sessionEditDTO.instructorUuid(), sessionEditDTO.traineeUuid());
                    var savedSessionDO = sessionRepository.save(sessionDO);
                    return sessionMapper.toDetailsDto(savedSessionDO);
                })
                .orElseThrow(() -> new NotFoundException("Session with uuid: " + uuid + " not found!"));
    }

    private void findSetObjectsByUuid(SessionDO sessionDO, String vehicleUuid, String instructorUuid, String traineeUuid) {
        if (vehicleUuid != null) {
            vehicleRepository.findById(vehicleUuid).ifPresentOrElse(sessionDO::setVehicle, () -> {
                throw new NotFoundException("Vehicle with uuid: " + vehicleUuid + " not found!");
            });
        }
        if (instructorUuid != null) {
            instructorRepository.findById(instructorUuid).ifPresentOrElse(sessionDO::setInstructor, () -> {
                throw new NotFoundException("Instructor with uuid: " + instructorUuid + " not found!");
            });
        }
        if (traineeUuid != null) {
            traineeRepository.findById(traineeUuid).ifPresentOrElse(sessionDO::setTrainee, () -> {
                throw new NotFoundException("Trainee with uuid: " + traineeUuid + " not found!");
            });
        }
    }
}
