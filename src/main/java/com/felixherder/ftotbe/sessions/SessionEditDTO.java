package com.felixherder.ftotbe.sessions;

import jakarta.validation.constraints.FutureOrPresent;

import java.time.ZonedDateTime;

public record SessionEditDTO(
        @FutureOrPresent
        ZonedDateTime scheduledAt,

        String traineeUuid,

        String instructorUuid,

        String vehicleUuid
) {
}
