package com.felixherder.ftotbe.vehicles;

import com.felixherder.ftotbe.sessions.SessionMapper;
import org.mapstruct.*;

@Mapper(nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        uses = SessionMapper.class)
public interface VehicleMapper {
    VehicleSummaryDTO toSummaryDto(final VehicleDO vehicleDO);

    VehicleDetailsDTO toDetailsDto(final VehicleDO vehicleDO);

    VehicleDO toDO(final VehicleDetailsDTO vehicleDetailsDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDoFromDto(VehicleDetailsDTO vehicleDetailsDTO, @MappingTarget VehicleDO vehicleDO);
}
