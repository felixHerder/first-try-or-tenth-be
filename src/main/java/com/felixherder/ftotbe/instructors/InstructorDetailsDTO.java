package com.felixherder.ftotbe.instructors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.felixherder.ftotbe.profiles.ProfileDTO;
import com.felixherder.ftotbe.sessions.SessionSummaryDTO;
import com.felixherder.ftotbe.trainees.TraineeSummaryDTO;
import com.felixherder.ftotbe.vehicles.VehicleSummaryDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record InstructorDetailsDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String uuid,

        @NotNull(message = "Profile is required!")
        @Valid
        ProfileDTO profile,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Set<VehicleSummaryDTO> vehicles,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Set<TraineeSummaryDTO> trainees,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Set<SessionSummaryDTO> sessions
) {
}
