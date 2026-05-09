package com.felixherder.ftotbe.instructors;


import com.felixherder.ftotbe.profiles.ProfileDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface InstructorService {
    List<InstructorSummaryDTO> getAll();

    InstructorDetailsDTO getByUuid(String uuid);

    InstructorDetailsDTO createInstructor(InstructorDetailsDTO instructorDetailsDTO);

    InstructorDetailsDTO updateInstructorProfile(String uuid, @Valid ProfileDTO instructorDetailsDTO);

    InstructorDetailsDTO updateInstructorVehicles(String uuid, List<String> vehicleUuids);

    InstructorDetailsDTO updateInstructorTrainees(String uuid, List<String> traineeUuids);

    InstructorDetailsDTO updateInstructorSessions(String uuid, List<String> sessionUuids);

    void deleteInstructor(String uuid);
}
