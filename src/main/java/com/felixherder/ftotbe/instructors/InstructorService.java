package com.felixherder.ftotbe.instructors;


import java.util.List;

public interface InstructorService {
    List<InstructorSummaryDTO> getAll();
    InstructorDetailsDTO getByUuid(String uuid);
}
