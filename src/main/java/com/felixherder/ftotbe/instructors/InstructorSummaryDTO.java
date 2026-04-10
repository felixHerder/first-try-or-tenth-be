package com.felixherder.ftotbe.instructors;

import com.felixherder.ftotbe.profiles.ProfileDTO;
public record InstructorSummaryDTO(
        String uuid,
        ProfileDTO profile
) {
}
