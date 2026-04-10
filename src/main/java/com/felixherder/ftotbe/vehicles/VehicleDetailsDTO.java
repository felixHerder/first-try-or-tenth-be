package com.felixherder.ftotbe.vehicles;

import com.felixherder.ftotbe.instructors.InstructorSummaryDTO;

import java.time.Year;
import java.util.Set;

public record VehicleDetailsDTO(
        String uuid,
        String model,
        String make,
        Year year,
        String licensePlate,
        String color,
        int engineTypeId,
        int fuelTypeId,
        int transmissionTypeId,
        String imageUrl,
        Set<InstructorSummaryDTO> instructors
//        Set<TraineeDO> trainees,
//        Set<SessionDO> session
) {
}
