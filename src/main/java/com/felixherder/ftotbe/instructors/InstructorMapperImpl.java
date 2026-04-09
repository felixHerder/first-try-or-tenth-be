package com.felixherder.ftotbe.instructors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InstructorMapperImpl implements InstructorMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public InstructorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public InstructorDTO mapDoToDto(InstructorDO instructorDO) {
        return modelMapper.map(instructorDO, InstructorDTO.class);
    }

    @Override
    public InstructorDO mapDtoToDo(InstructorDTO instructorDTO) {
        return modelMapper.map(instructorDTO, InstructorDO.class);
    }

    @Override
    public List<InstructorDTO> mapDoListToDtoList(List<InstructorDO> instructorDOs) {
        return instructorDOs.stream().map(this::mapDoToDto).toList();
    }
}
