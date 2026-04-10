package com.felixherder.ftotbe.vehicles;

import java.time.Year;


public record VehicleSummaryDTO(
        String uuid,
        String model,
        String make,
        Year year,
        int transmissionTypeId,
        String imageUrl
) {
}
