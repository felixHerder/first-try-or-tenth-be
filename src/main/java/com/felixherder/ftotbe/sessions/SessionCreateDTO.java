package com.felixherder.ftotbe.sessions;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

public record SessionCreateDTO(
        @NotNull(message = "Scheduled date and time are required!")
        @FutureOrPresent
        ZonedDateTime scheduledAt,

        @NotBlank(message = "Trainee uuid is required!")
        String traineeUuid,

        @NotBlank(message = "Instructor uuid is required!")
        String instructorUuid,

        @NotBlank(message = "Vehicle uuid is required!")
        String vehicleUuid
) {
}
