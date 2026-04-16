package com.felixherder.ftotbe.instructors;

import com.felixherder.ftotbe.exceptions.NotFoundException;
import com.felixherder.ftotbe.sessions.SessionRepository;
import com.felixherder.ftotbe.trainees.TraineeRepository;
import com.felixherder.ftotbe.vehicles.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final VehicleRepository vehicleRepository;
    private final TraineeRepository traineeRepository;
    private final SessionRepository sessionRepository;
    private final InstructorMapper instructorMapper;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository,
                                 VehicleRepository vehicleRepository,
                                 TraineeRepository traineeRepository,
                                 SessionRepository sessionRepository,
                                 InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.vehicleRepository = vehicleRepository;
        this.traineeRepository = traineeRepository;
        this.sessionRepository = sessionRepository;
        this.instructorMapper = instructorMapper;
    }

    @Override
    public List<InstructorSummaryDTO> getAll() {
        var instructors = instructorRepository.findAll();
        return instructors.stream().map(instructorMapper::toSummaryDto).toList();
    }

    @Override
    public InstructorDetailsDTO getByUuid(String uuid) {
        return instructorRepository.findById(uuid)
                .map(instructorMapper::toDetailsDto)
                .orElseThrow(() -> new NotFoundException("Instructor with uuid: " + uuid + " not found!"));
    }

    @Override
    public InstructorDetailsDTO createInstructor(InstructorDetailsDTO instructorDetailsDTO) {
        var savedInstructorDO = instructorRepository.save(instructorMapper.toDO(instructorDetailsDTO));
        return instructorMapper.toDetailsDto(savedInstructorDO);
    }

    @Override
    public InstructorDetailsDTO updateInstructorProfile(String uuid, InstructorDetailsDTO instructorDetailsDTO) {
        return instructorRepository.findById(uuid)
                .map(instructorDO -> {
                    instructorMapper.updateDoFromDto(instructorDetailsDTO, instructorDO);
                    var savedInstructorDO = instructorRepository.save(instructorDO);
                    return instructorMapper.toDetailsDto(savedInstructorDO);
                })
                .orElseThrow(() -> new NotFoundException("Instructor with uuid: " + uuid + " not found!"));
    }

    @Override
    public InstructorDetailsDTO updateInstructorVehicles(String uuid, List<String> vehicleUuids) {
        return instructorRepository.findById(uuid)
                .map(instructorDO -> {
                    var newVehicles = vehicleRepository.findAllById(vehicleUuids);
                    var oldVehicles = new LinkedHashSet<>(instructorDO.getVehicles());
                    oldVehicles.forEach(instructorDO::removeVehicle);
                    newVehicles.forEach(instructorDO::addVehicle);
                    var savedInstructorDO = instructorRepository.save(instructorDO);
                    return instructorMapper.toDetailsDto(savedInstructorDO);
                })
                .orElseThrow(() -> new NotFoundException("Instructor with uuid: " + uuid + " not found!"));
    }

    @Override
    public InstructorDetailsDTO updateInstructorTrainees(String uuid, List<String> traineeUuids) {
        return instructorRepository.findById(uuid)
                .map(instructorDO -> {
                    var newTrainees = traineeRepository.findAllById(traineeUuids);
                    var oldTrainees = new LinkedHashSet<>(instructorDO.getTrainees());
                    oldTrainees.forEach(instructorDO::removeTrainee);
                    newTrainees.forEach(instructorDO::addTrainee);
                    var savedInstructorDO = instructorRepository.save(instructorDO);
                    return instructorMapper.toDetailsDto(savedInstructorDO);
                })
                .orElseThrow(() -> new NotFoundException("Instructor with uuid: " + uuid + " not found!"));
    }

    @Override
    public InstructorDetailsDTO updateInstructorSessions(String uuid, List<String> sessionUuids) {
        return instructorRepository.findById(uuid)
                .map(instructorDO -> {
                    var newSessions = sessionRepository.findAllById(sessionUuids);
                    var oldSessions = new LinkedHashSet<>(instructorDO.getSessions());
                    oldSessions.forEach(instructorDO::removeSession);
                    newSessions.forEach(instructorDO::addSession);
                    var savedInstructorDO = instructorRepository.save(instructorDO);
                    return instructorMapper.toDetailsDto(savedInstructorDO);
                })
                .orElseThrow(() -> new NotFoundException("Instructor with uuid: " + uuid + " not found!"));
    }
}
