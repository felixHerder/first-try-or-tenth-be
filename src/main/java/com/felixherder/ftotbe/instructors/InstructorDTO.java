package com.felixherder.ftotbe.instructors;

import com.felixherder.ftotbe.profiles.ProfileDTO;
import lombok.Data;

@Data
public class InstructorDTO {
    private String uuid;
    private ProfileDTO profile;
}
