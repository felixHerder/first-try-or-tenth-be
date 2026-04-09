package com.felixherder.ftotbe.vehicles;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleMapperImpl implements VehicleMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public VehicleMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public VehicleDTO mapDoToDto(VehicleDO vehicleDO) {
        return modelMapper.map(vehicleDO, VehicleDTO.class);
    }

    @Override
    public VehicleDO mapDtoToDo(VehicleDTO vehicleDTO) {
        return modelMapper.map(vehicleDTO, VehicleDO.class);
    }

    @Override
    public List<VehicleDTO> mapDoListToDtoList(List<VehicleDO> vehicleDOs) {
        return vehicleDOs.stream().map(this::mapDoToDto).toList();
    }
}
