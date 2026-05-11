package com.felixherder.ftotbe.trainees;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.felixherder.ftotbe.instructors.InstructorSummaryDTO;
import com.felixherder.ftotbe.profiles.ProfileDTO;
import com.felixherder.ftotbe.sessions.SessionSummaryDTO;
import com.felixherder.ftotbe.vehicles.VehicleSummaryDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record TraineeDetailsDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String uuid,

        @NotNull(message = "Profile is required!")
        @Valid
        ProfileDTO profile,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        InstructorSummaryDTO instructor,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        VehicleSummaryDTO vehicle,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Set<SessionSummaryDTO> sessions
) {
}

