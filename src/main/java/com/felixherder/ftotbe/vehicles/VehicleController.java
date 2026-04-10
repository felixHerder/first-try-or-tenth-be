package com.felixherder.ftotbe.vehicles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
