package com.felixherder.ftotbe.instructors;

import com.felixherder.ftotbe.profiles.ProfileDTO;
import jakarta.validation.constraints.NotNull;

public record InstructorSummaryDTO(
        @NotNull
        String uuid,
        @NotNull
        ProfileDTO profile
) {
}
