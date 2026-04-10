package com.felixherder.ftotbe.sessions;

import com.felixherder.ftotbe.vehicles.VehicleDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface SessionMapper {
    @Mapping(target = "traineeName", source = "trainee.profile.name")
    @Mapping(target = "instructorName", source = "instructor.profile.name")
    @Mapping(target = "vehicleOutline", source = "vehicle")
    SessionSummaryDTO toSummaryDto(SessionDO sessionDO);

    default String mapVehicle(VehicleDO vehicleDO) {
        if (vehicleDO == null) return null;
        return vehicleDO.getMake() + " "
                + vehicleDO.getModel() + " "
                + vehicleDO.getYear();
    }

    SessionDetailsDTO toDetailsDto(SessionDO sessionDO);
}
