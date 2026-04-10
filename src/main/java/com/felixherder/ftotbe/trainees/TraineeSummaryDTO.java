package com.felixherder.ftotbe.trainees;

import com.felixherder.ftotbe.profiles.ProfileDTO;

public record TraineeSummaryDTO(
        String uuid,
        ProfileDTO profile
) {
}
