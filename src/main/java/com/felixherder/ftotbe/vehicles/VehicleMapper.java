package com.felixherder.ftotbe.vehicles;

import java.util.List;

public interface VehicleMapper {
    VehicleDTO mapDoToDto(final VehicleDO vehicleDO);
    VehicleDO mapDtoToDo(final VehicleDTO vehicleDTO);
    List<VehicleDTO> mapDoListToDtoList(final List<VehicleDO> vehicleDOs);
}
