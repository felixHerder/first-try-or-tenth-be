package com.felixherder.ftotbe.vehicles;

import com.felixherder.ftotbe.vehicles.enums.EngineType;
import com.felixherder.ftotbe.vehicles.enums.TransmissionType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Year;

@Builder
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
        @NotNull
        EngineType engineType,
        String imageUrl
) {
}
