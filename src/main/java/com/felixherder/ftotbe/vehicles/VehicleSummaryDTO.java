package com.felixherder.ftotbe.vehicles;

import com.felixherder.ftotbe.vehicles.enums.TransmissionType;

import java.time.Year;


public record VehicleSummaryDTO(
        String uuid,
        String model,
        String make,
        Year year,
        TransmissionType transmissionType,
        String imageUrl
) {
}
