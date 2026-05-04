package com.felixherder.ftotbe.trainees;

import com.felixherder.ftotbe.profiles.ProfileDTO;
import jakarta.validation.constraints.NotNull;

public record TraineeSummaryDTO(
        @NotNull
        String uuid,
        @NotNull
        ProfileDTO profile
) {
}
