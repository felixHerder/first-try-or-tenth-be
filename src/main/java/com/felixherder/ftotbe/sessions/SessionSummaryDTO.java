package com.felixherder.ftotbe.sessions;

import java.time.ZonedDateTime;

public record SessionSummaryDTO(
        String uuid,
        ZonedDateTime scheduledAt,
        String traineeName,
        String instructorName,
        String vehicleOutline
) {
}
