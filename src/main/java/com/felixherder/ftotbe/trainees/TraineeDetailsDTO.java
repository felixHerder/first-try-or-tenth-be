package com.felixherder.ftotbe.trainees;

import com.felixherder.ftotbe.instructors.InstructorSummaryDTO;
import com.felixherder.ftotbe.profiles.ProfileDTO;
import com.felixherder.ftotbe.sessions.SessionSummaryDTO;
import com.felixherder.ftotbe.vehicles.VehicleSummaryDTO;

import java.util.Set;

public record TraineeDetailsDTO(
        ProfileDTO profile,
        InstructorSummaryDTO instructor,
        VehicleSummaryDTO vehicle,
        Set<SessionSummaryDTO> session
) {
}

