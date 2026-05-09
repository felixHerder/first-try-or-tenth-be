package com.felixherder.ftotbe.instructors;

import com.felixherder.ftotbe.profiles.ProfileDTO;
import com.felixherder.ftotbe.sessions.SessionMapper;
import org.mapstruct.*;

@Mapper(nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        uses = SessionMapper.class)
public interface InstructorMapper {
    InstructorSummaryDTO toSummaryDto(final InstructorDO instructorDO);

    InstructorDetailsDTO toDetailsDto(final InstructorDO instructorDO);

    InstructorDO toDO(final InstructorDetailsDTO instructorDetailsDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDoFromDto(InstructorDetailsDTO instructorDetailsDTO, @MappingTarget InstructorDO instructorDO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = ".", target = "profile")
    void updateDoFromProfileDto(ProfileDTO profileDto, @MappingTarget InstructorDO instructorDO);
}

