package com.felixherder.ftotbe.vehicles;

import com.felixherder.ftotbe.vehicles.enums.TransmissionType;
import jakarta.validation.constraints.NotNull;

import java.time.Year;


public record VehicleSummaryDTO(
        @NotNull
        String uuid,
        @NotNull
        String model,
        @NotNull
        String make,
        @NotNull
        Year year,
        @NotNull
        TransmissionType transmissionType,
        String imageUrl
) {
}
