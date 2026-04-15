package com.felixherder.ftotbe.instructors;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface InstructorMapper {
    InstructorSummaryDTO toSummaryDto(final InstructorDO instructorDO);

    InstructorDetailsDTO toDetailsDto(final InstructorDO instructorDO);

    InstructorDO toDO(final InstructorDetailsDTO instructorDetailsDTO);

    void updateDoFromDto(InstructorDetailsDTO instructorDetailsDTO, @MappingTarget InstructorDO instructorDO);
}
