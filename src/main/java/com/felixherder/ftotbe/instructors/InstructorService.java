package com.felixherder.ftotbe.instructors;


import java.util.List;

public interface InstructorService {
    List<InstructorDTO> getAll();
    InstructorDTO getByUuid(String uuid);
}
