package com.felixherder.ftotbe.trainees;

import com.felixherder.ftotbe.sessions.SessionMapper;
import org.mapstruct.*;

@Mapper(nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        uses = SessionMapper.class)
public interface TraineeMapper {
    TraineeSummaryDTO toSummaryDto(TraineeDO traineeDO);

    TraineeDetailsDTO toDetailsDto(TraineeDO traineeDO);

    TraineeDO toDO(TraineeDetailsDTO traineeDetailsDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDoFromDto(TraineeDetailsDTO traineeDetailsDTO, @MappingTarget TraineeDO traineeDO);
}
