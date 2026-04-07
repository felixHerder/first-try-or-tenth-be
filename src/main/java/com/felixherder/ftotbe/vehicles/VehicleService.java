package com.felixherder.ftotbe.vehicles;

import java.util.List;

public interface VehicleService {
    List<VehicleDTO> getAll();
    VehicleDTO getByUuid(String uuid);
}
