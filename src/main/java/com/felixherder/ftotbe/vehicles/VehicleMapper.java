package com.felixherder.ftotbe.vehicles;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface VehicleMapper {
    VehicleSummaryDTO toSummaryDto (final VehicleDO vehicleDO);
    VehicleDetailsDTO toDetailsDto (final VehicleDO vehicleDO);
}
