package com.felixherder.ftotbe.vehicles;

import com.felixherder.ftotbe.instructors.InstructorSummaryDTO;
import com.felixherder.ftotbe.trainees.TraineeSummaryDTO;

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
        Set<InstructorSummaryDTO> instructors,
        Set<TraineeSummaryDTO> trainees
//        Set<SessionDO> session
) {
}
