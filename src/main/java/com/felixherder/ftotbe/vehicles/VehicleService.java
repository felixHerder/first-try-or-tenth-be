package com.felixherder.ftotbe.vehicles;

import jakarta.validation.Valid;

import java.util.List;

public interface VehicleService {
    List<VehicleSummaryDTO> getAll();

    VehicleDetailsDTO getByUuid(String uuid);

    VehicleDetailsDTO createVehicle(VehicleDetailsDTO vehicleDetailsDTO);

    VehicleDetailsDTO updateVehicleDetails(String uuid, VehicleDetailsDTO vehicleDetailsDTO);

    VehicleDetailsDTO updateVehicleInstructors(String uuid, @Valid List<String> instructorUuids);

    VehicleDetailsDTO updateVehicleTrainees(String uuid, @Valid List<String> traineeUuids);

    VehicleDetailsDTO updateVehicleSessions(String uuid, List<String> sessionUuids);
}
