package com.felixherder.ftotbe.trainees;

import org.mapstruct.*;

@Mapper(nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface TraineeMapper {
    TraineeSummaryDTO toSummaryDto(TraineeDO traineeDO);

    TraineeDetailsDTO toDetailsDto(TraineeDO traineeDO);

    TraineeDO toDO(TraineeDetailsDTO traineeDetailsDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDoFromDto(TraineeDetailsDTO traineeDetailsDTO, @MappingTarget TraineeDO traineeDO);
}
