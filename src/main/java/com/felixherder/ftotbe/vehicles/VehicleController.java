package com.felixherder.ftotbe.vehicles;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<VehicleSummaryDTO> getAll() {
        return vehicleService.getAll();
    }

    @GetMapping("/{uuid}")
    public VehicleDetailsDTO getById(@PathVariable String uuid) {
        return vehicleService.getByUuid(uuid);
    }

    @PostMapping()
    public VehicleDetailsDTO createVehicle(@Valid @RequestBody final VehicleDetailsDTO vehicleDetailsDTO) {
        return vehicleService.createVehicle(vehicleDetailsDTO);
    }
}
