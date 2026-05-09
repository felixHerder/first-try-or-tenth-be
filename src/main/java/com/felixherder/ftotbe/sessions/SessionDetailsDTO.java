package com.felixherder.ftotbe.sessions;

import com.felixherder.ftotbe.instructors.InstructorSummaryDTO;
import com.felixherder.ftotbe.trainees.TraineeSummaryDTO;
import com.felixherder.ftotbe.vehicles.VehicleSummaryDTO;

import java.time.ZonedDateTime;

public record SessionDetailsDTO(
        String uuid,

        ZonedDateTime scheduledAt,

        TraineeSummaryDTO trainee,

        InstructorSummaryDTO instructor,

        VehicleSummaryDTO vehicle
) {
}
