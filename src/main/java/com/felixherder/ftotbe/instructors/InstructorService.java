package com.felixherder.ftotbe.instructors;


import java.util.List;

public interface InstructorService {
    List<InstructorSummaryDTO> getAll();

    InstructorDetailsDTO getByUuid(String uuid);

    InstructorDetailsDTO createInstructor(InstructorDetailsDTO instructorDetailsDTO);

    InstructorDetailsDTO updateInstructorProfile(String uuid, InstructorDetailsDTO instructorDetailsDTO);

    InstructorDetailsDTO updateInstructorVehicles(String uuid, List<String> vehicleUuids);

    InstructorDetailsDTO updateInstructorTrainees(String uuid, List<String> traineeUuids);

    InstructorDetailsDTO updateInstructorSessions(String uuid, List<String> sessionUuids);

    void deleteSession(String uuid);
}
