package com.felixherder.ftotbe.sessions;

import com.felixherder.ftotbe.vehicles.VehicleDO;
import org.mapstruct.*;

@Mapper(nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface SessionMapper {
    @Mapping(target = "traineeName", source = "trainee.profile.name")
    @Mapping(target = "instructorName", source = "instructor.profile.name")
    @Mapping(target = "vehicleOutline", source = "vehicle")
    SessionSummaryDTO toSummaryDto(SessionDO sessionDO);

    SessionDetailsDTO toDetailsDto(SessionDO sessionDO);

    SessionDO toDO(SessionCreateDTO sessionCreateDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDoFromDto(SessionEditDTO sessionEditDTO, @MappingTarget SessionDO sessionDO);

    default String mapVehicle(VehicleDO vehicleDO) {
        if (vehicleDO == null) return null;
        return vehicleDO.getMake() + " "
                + vehicleDO.getModel() + " "
                + vehicleDO.getYear();
    }
}
