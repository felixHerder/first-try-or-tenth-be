package com.felixherder.ftotbe.vehicles;

import com.felixherder.ftotbe.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public List<VehicleDTO> getAll() {
        var vehicles = vehicleRepository.findAll();
        return vehicleMapper.mapDoListToDtoList(vehicles);
    }

    @Override
    public VehicleDTO getByUuid(String uuid) {
        return vehicleRepository.findById(uuid)
                .map(vehicleMapper::mapDoToDto)
                .orElseThrow(() -> new NotFoundException("Vehicle with uuid: " + uuid + " not found!"));
    }
}
