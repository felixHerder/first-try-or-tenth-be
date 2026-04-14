package com.felixherder.ftotbe.vehicles;

import java.util.List;

public interface VehicleService {
    List<VehicleSummaryDTO> getAll();

    VehicleDetailsDTO getByUuid(String uuid);

    VehicleDetailsDTO createVehicle(VehicleDetailsDTO vehicleDetailsDTO);
}
