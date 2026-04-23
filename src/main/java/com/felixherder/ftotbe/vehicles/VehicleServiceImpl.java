package com.felixherder.ftotbe.vehicles;

import com.felixherder.ftotbe.exceptions.NotFoundException;
import com.felixherder.ftotbe.instructors.InstructorRepository;
import com.felixherder.ftotbe.sessions.SessionRepository;
import com.felixherder.ftotbe.trainees.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final InstructorRepository instructorRepository;
    private final TraineeRepository traineeRepository;
    private final SessionRepository sessionRepository;
    private final VehicleMapper vehicleMapper;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository,
                              InstructorRepository instructorRepository,
                              TraineeRepository traineeRepository,
                              SessionRepository sessionRepository,
                              VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.instructorRepository = instructorRepository;
        this.traineeRepository = traineeRepository;
        this.sessionRepository = sessionRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public List<VehicleSummaryDTO> getAll() {
        var vehicles = vehicleRepository.findAll();
        return vehicles.stream().map(vehicleMapper::toSummaryDto).toList();
    }

    @Override
    public VehicleDetailsDTO getByUuid(String uuid) {
        return vehicleRepository.findById(uuid).map(vehicleMapper::toDetailsDto).orElseThrow(() -> new NotFoundException("Vehicle with uuid: " + uuid + " not found!"));
    }

    @Override
    public VehicleDetailsDTO createVehicle(VehicleDetailsDTO vehicleDetailsDTO) {
        var savedVehicleDO = vehicleRepository.save(vehicleMapper.toDO(vehicleDetailsDTO));
        return vehicleMapper.toDetailsDto(savedVehicleDO);
    }

    @Override
    public VehicleDetailsDTO updateVehicleDetails(String uuid, VehicleDetailsDTO vehicleDetailsDTO) {
        var currentVehicle = vehicleRepository.findById(uuid);
        return currentVehicle.map(vehicleDO -> {
            vehicleMapper.updateDoFromDto(vehicleDetailsDTO, vehicleDO);
            vehicleRepository.save(vehicleDO);
            return vehicleMapper.toDetailsDto(vehicleDO);
        }).orElseThrow(() -> new NotFoundException("Vehicle with uuid: " + uuid + " not found!"));
    }

    @Override
    public VehicleDetailsDTO updateVehicleInstructors(String uuid, List<String> instructorUuids) {
        var currentVehicle = vehicleRepository.findById(uuid);
        return currentVehicle.map(vehicleDO -> {
            var newInstructors = instructorRepository.findAllById(instructorUuids);
            var oldInstructors = new LinkedHashSet<>(vehicleDO.getInstructors());
            oldInstructors.forEach(vehicleDO::removeInstructor);
            newInstructors.forEach(vehicleDO::addInstructor);
            var savedVehicleDO = vehicleRepository.save(vehicleDO);
            return vehicleMapper.toDetailsDto(savedVehicleDO);
        }).orElseThrow(() -> new NotFoundException("Vehicle with uuid: " + uuid + " not found!"));
    }

    @Override
    public VehicleDetailsDTO updateVehicleTrainees(String uuid, List<String> traineeUuids) {
        var currentVehicle = vehicleRepository.findById(uuid);
        return currentVehicle.map(vehicleDO -> {
            var newTrainees = traineeRepository.findAllById(traineeUuids);
            var oldTrainees = new LinkedHashSet<>(vehicleDO.getTrainees());
            oldTrainees.forEach(vehicleDO::removeTrainee);
            newTrainees.forEach(vehicleDO::addTrainee);
            var savedVehicleDO = vehicleRepository.save(vehicleDO);
            return vehicleMapper.toDetailsDto(savedVehicleDO);
        }).orElseThrow(() -> new NotFoundException("Vehicle with uuid: " + uuid + " not found"));
    }

    @Override
    public VehicleDetailsDTO updateVehicleSessions(String uuid, List<String> sessionUuids) {
        var currentVehicle = vehicleRepository.findById(uuid);
        return currentVehicle.map(vehicleDO -> {
            var newSessions = sessionRepository.findAllById(sessionUuids);
            var oldSessions = new LinkedHashSet<>(vehicleDO.getSessions());
            oldSessions.forEach(vehicleDO::removeSession);
            newSessions.forEach(vehicleDO::addSession);
            var savedVehicleDO = vehicleRepository.save(vehicleDO);
            return vehicleMapper.toDetailsDto(savedVehicleDO);
        }).orElseThrow(() -> new NotFoundException("Vehicle with uuid: " + uuid + " not found"));
    }

    @Override
    public void deleteVehicle(String uuid) {
        vehicleRepository.deleteById(uuid);
    }
}
