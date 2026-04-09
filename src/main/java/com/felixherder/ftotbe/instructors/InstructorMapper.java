package com.felixherder.ftotbe.instructors;

import java.util.List;

public interface InstructorMapper {
    InstructorDTO mapDoToDto(final InstructorDO instructorDO);

    InstructorDO mapDtoToDo(final InstructorDTO instructorDTO);

    List<InstructorDTO> mapDoListToDtoList(final List<InstructorDO> instructorDOs);
}
