package com.felixherder.ftotbe.vehicles;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface VehicleMapper {
    VehicleSummaryDTO toSummaryDto(final VehicleDO vehicleDO);

    VehicleDetailsDTO toDetailsDto(final VehicleDO vehicleDO);

    VehicleDO toDO(final VehicleDetailsDTO vehicleDetailsDTO);

    void updateDoFromDto(VehicleDetailsDTO vehicleDetailsDTO, @MappingTarget VehicleDO vehicleDO);
}
