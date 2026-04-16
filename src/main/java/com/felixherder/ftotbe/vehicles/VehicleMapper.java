package com.felixherder.ftotbe.vehicles;

import org.mapstruct.*;

@Mapper(nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface VehicleMapper {
    VehicleSummaryDTO toSummaryDto(final VehicleDO vehicleDO);

    VehicleDetailsDTO toDetailsDto(final VehicleDO vehicleDO);

    VehicleDO toDO(final VehicleDetailsDTO vehicleDetailsDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDoFromDto(VehicleDetailsDTO vehicleDetailsDTO, @MappingTarget VehicleDO vehicleDO);
}
