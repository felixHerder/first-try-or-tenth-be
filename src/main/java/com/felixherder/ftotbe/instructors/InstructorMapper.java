package com.felixherder.ftotbe.instructors;

import org.mapstruct.*;

@Mapper(nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface InstructorMapper {
    InstructorSummaryDTO toSummaryDto(final InstructorDO instructorDO);

    InstructorDetailsDTO toDetailsDto(final InstructorDO instructorDO);

    InstructorDO toDO(final InstructorDetailsDTO instructorDetailsDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDoFromDto(InstructorDetailsDTO instructorDetailsDTO, @MappingTarget InstructorDO instructorDO);
}
