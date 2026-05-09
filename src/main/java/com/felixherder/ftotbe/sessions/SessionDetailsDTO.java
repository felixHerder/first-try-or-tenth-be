package com.felixherder.ftotbe.sessions;

import com.felixherder.ftotbe.instructors.InstructorSummaryDTO;
import com.felixherder.ftotbe.trainees.TraineeSummaryDTO;
import com.felixherder.ftotbe.vehicles.VehicleSummaryDTO;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

public record SessionDetailsDTO(
        @NotNull
        String uuid,

        @NotNull
        ZonedDateTime scheduledAt,

        @NotNull
        TraineeSummaryDTO trainee,

        @NotNull
        InstructorSummaryDTO instructor,

        @NotNull
        VehicleSummaryDTO vehicle
) {
}
