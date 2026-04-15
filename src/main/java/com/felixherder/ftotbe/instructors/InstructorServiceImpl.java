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
    public InstructorServiceImpl(InstructorRepository instructorRepository, VehicleRepository vehicleRepository, TraineeRepository traineeRepository, SessionRepository sessionRepository, InstructorMapper instructorMapper) {
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
                    String profileUuid = instructorDO.getProfile().getUuid();
                    instructorMapper.updateDoFromDto(instructorDetailsDTO, instructorDO);
                    instructorDO.setUuid(uuid);
                    instructorDO.getProfile().setUuid(profileUuid);
                    instructorRepository.save(instructorDO);
                    return instructorMapper.toDetailsDto(instructorDO);
                })
                .orElseThrow(() -> new NotFoundException("Instructor with uuid: " + uuid + " not found!"));
    }

    @Override
    public InstructorDetailsDTO updateInstructorVehicles(String uuid, List<String> vehicleUuids) {
        return instructorRepository.findById(uuid)
                .map(instructorDO -> {
                    var vehicles = vehicleRepository.findAllById(vehicleUuids);
                    instructorDO.setVehicles(new LinkedHashSet<>(vehicles));
                    return instructorMapper.toDetailsDto(instructorDO);
                })
                .orElseThrow(() -> new NotFoundException("Instructor with uuid: " + uuid + " not found!"));
    }

    @Override
    public InstructorDetailsDTO updateInstructorTrainees(String uuid, List<String> traineeUuids) {
        return instructorRepository.findById(uuid)
                .map(instructorDO -> {
                    var trainees = traineeRepository.findAllById(traineeUuids);
                    instructorDO.setTrainees(new LinkedHashSet<>(trainees));
                    return instructorMapper.toDetailsDto(instructorDO);
                })
                .orElseThrow(() -> new NotFoundException("Instructor with uuid: " + uuid + " not found!"));
    }

    @Override
    public InstructorDetailsDTO updateInstructorSessions(String uuid, List<String> sessionUuids) {
        return instructorRepository.findById(uuid)
                .map(instructorDO -> {
                    var sessions = sessionRepository.findAllById(sessionUuids);
                    instructorDO.setSessions(new LinkedHashSet<>(sessions));
                    return instructorMapper.toDetailsDto(instructorDO);
                })
                .orElseThrow(() -> new NotFoundException("Instructor with uuid: " + uuid + " not found!"));
    }
}
