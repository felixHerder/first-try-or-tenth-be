package com.felixherder.ftotbe.trainees;

import com.felixherder.ftotbe.instructors.InstructorSummaryDTO;
import com.felixherder.ftotbe.profiles.ProfileDTO;
import com.felixherder.ftotbe.vehicles.VehicleSummaryDTO;

public record TraineeDetailsDTO (
        ProfileDTO profile,
        InstructorSummaryDTO instructor,
        VehicleSummaryDTO vehicle
//     final Set<SessionDTO> session
){}

