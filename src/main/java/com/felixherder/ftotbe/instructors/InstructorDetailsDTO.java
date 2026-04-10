package com.felixherder.ftotbe.instructors;

import com.felixherder.ftotbe.profiles.ProfileDTO;
import com.felixherder.ftotbe.vehicles.VehicleSummaryDTO;

import java.util.Set;

public record InstructorDetailsDTO(
        String uuid,
        ProfileDTO profile,
        Set<VehicleSummaryDTO> vehicles
//        Set<TraineeDO> trainees,
//        Set<SessionDO> sessions
) {
}
