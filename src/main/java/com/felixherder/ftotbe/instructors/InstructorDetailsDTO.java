package com.felixherder.ftotbe.instructors;

import com.felixherder.ftotbe.profiles.ProfileDTO;
import com.felixherder.ftotbe.trainees.TraineeSummaryDTO;
import com.felixherder.ftotbe.vehicles.VehicleSummaryDTO;

import java.util.Set;

public record InstructorDetailsDTO(
        String uuid,
        ProfileDTO profile,
        Set<VehicleSummaryDTO> vehicles,
        Set<TraineeSummaryDTO> trainees
//        Set<SessionDO> sessions
) {
}
