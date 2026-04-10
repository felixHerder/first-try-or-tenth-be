package com.felixherder.ftotbe.trainees;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface TraineeMapper {
    TraineeSummaryDTO toSummaryDto(TraineeDO traineeDO);
    TraineeDetailsDTO toDetailsDto(TraineeDO traineeDO);
}
