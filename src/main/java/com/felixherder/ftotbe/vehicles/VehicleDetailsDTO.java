package com.felixherder.ftotbe.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.felixherder.ftotbe.instructors.InstructorSummaryDTO;
import com.felixherder.ftotbe.sessions.SessionSummaryDTO;
import com.felixherder.ftotbe.trainees.TraineeSummaryDTO;
import com.felixherder.ftotbe.vehicles.enums.EngineType;
import com.felixherder.ftotbe.vehicles.enums.FuelType;
import com.felixherder.ftotbe.vehicles.enums.TransmissionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.Year;
import java.util.Set;

public record VehicleDetailsDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String uuid,

        @NotBlank(message = "Model is required!")
        String model,

        @NotBlank(message = "Make is required!")
        String make,

        @NotNull(message = "Year is required!")
        @PastOrPresent(message = "Year cannot be in the future!")
        Year year,

        @NotBlank(message = "License is required!")
        @Size(min = 3, max = 15, message = "License needs to be between 3 and 15 characters!")
        String licensePlate,

        @NotBlank(message = "Color is required!")
        String color,

        @NotNull(message = "Engine type is required!")
        EngineType engineType,

        @NotNull(message = "Fuel type is required!")
        FuelType fuelType,

        @NotNull(message = "Transmission Type is required!")
        TransmissionType transmissionType,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String imageUrl,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Set<InstructorSummaryDTO> instructors,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Set<TraineeSummaryDTO> trainees,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Set<SessionSummaryDTO> sessions
) {
}
