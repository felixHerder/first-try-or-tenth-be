package com.felixherder.ftotbe.trainees;

import com.felixherder.ftotbe.exceptions.NotFoundException;
import com.felixherder.ftotbe.instructors.InstructorRepository;
import com.felixherder.ftotbe.sessions.SessionRepository;
import com.felixherder.ftotbe.vehicles.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;

@Service
public class TraineeServiceImpl implements TraineeService {
    private final TraineeRepository traineeRepository;
    private final VehicleRepository vehicleRepository;
    private final InstructorRepository instructorRepository;
    private final SessionRepository sessionRepository;
    private final TraineeMapper traineeMapper;

    @Autowired
    public TraineeServiceImpl(TraineeRepository traineeRepository,
                              VehicleRepository vehicleRepository,
                              InstructorRepository instructorRepository,
                              SessionRepository sessionRepository,
                              TraineeMapper traineeMapper) {
        this.traineeRepository = traineeRepository;
        this.vehicleRepository = vehicleRepository;
        this.instructorRepository = instructorRepository;
        this.sessionRepository = sessionRepository;
        this.traineeMapper = traineeMapper;
    }

    @Override
    public List<TraineeSummaryDTO> getAll() {
        var trainees = traineeRepository.findAll();
        return trainees.stream().map(traineeMapper::toSummaryDto).toList();
    }

    @Override
    public TraineeDetailsDTO getByUuid(String uuid) {
        return traineeRepository.findById(uuid)
                .map(traineeMapper::toDetailsDto)
                .orElseThrow(() -> new NotFoundException("Trainee with uuid: " + uuid + " not found!"));
    }

    @Override
    public TraineeDetailsDTO createTrainee(TraineeDetailsDTO traineeDetailsDTO) {
        var savedTraineeDO = traineeRepository.save(traineeMapper.toDO(traineeDetailsDTO));
        return traineeMapper.toDetailsDto(savedTraineeDO);
    }

    @Override
    public TraineeDetailsDTO updateTraineeProfile(String uuid, TraineeDetailsDTO traineeDetailsDTO) {
        return traineeRepository.findById(uuid).map(traineeDO -> {
            traineeMapper.updateDoFromDto(traineeDetailsDTO, traineeDO);
            var savedTraineeDO = traineeRepository.save(traineeDO);
            return traineeMapper.toDetailsDto(savedTraineeDO);
        }).orElseThrow(() -> new NotFoundException("Trainee with uuid: " + uuid + " not found!"));
    }

    @Override
    public TraineeDetailsDTO updateTraineeVehicle(String uuid, String vehicleUuid) {
        return traineeRepository.findById(uuid).map(traineeDO -> {
            vehicleRepository.findById(vehicleUuid).ifPresentOrElse(traineeDO::setVehicle, () -> {
                throw new NotFoundException("Vehicle with uuid: " + vehicleUuid + " not found!");
            });
            var savedTraineeDO = traineeRepository.save(traineeDO);
            return traineeMapper.toDetailsDto(savedTraineeDO);
        }).orElseThrow(() -> new NotFoundException("Trainee with uuid: " + uuid + " not found!"));
    }

    @Override
    public TraineeDetailsDTO updateTraineeInstructor(String uuid, String instructorUuid) {
        return traineeRepository.findById(uuid).map(traineeDO -> {
            instructorRepository.findById(instructorUuid).ifPresentOrElse(traineeDO::setInstructor, () -> {
                throw new NotFoundException("Instructor with uuid: " + instructorUuid + " not found!");
            });
            var savedTraineeDO = traineeRepository.save(traineeDO);
            return traineeMapper.toDetailsDto(savedTraineeDO);
        }).orElseThrow(() -> new NotFoundException("Trainee with uuid: " + uuid + " not found!"));
    }

    @Override
    public TraineeDetailsDTO updateTraineeSessions(String uuid, List<String> sessionUuids) {
        return traineeRepository.findById(uuid).map(traineeDO -> {
            var newSessions = sessionRepository.findAllById(sessionUuids);
            var oldSessions = new LinkedHashSet<>(traineeDO.getSessions());
            oldSessions.forEach(traineeDO::removeSession);
            newSessions.forEach(traineeDO::addSession);
            var savedTraineeDO = traineeRepository.save(traineeDO);
            return traineeMapper.toDetailsDto(savedTraineeDO);
        }).orElseThrow(() -> new NotFoundException("Instructor with uuid: " + uuid + " not found!"));
    }

    @Override
    public void deleteSession(String uuid) {
        traineeRepository.deleteById(uuid);
    }
}
