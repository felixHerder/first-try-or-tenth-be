package com.felixherder.ftotbe.vehicles;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleDetailsDTO createVehicle(@Valid @RequestBody final VehicleDetailsDTO vehicleDetailsDTO) {
        return vehicleService.createVehicle(vehicleDetailsDTO);
    }

    @PutMapping("/{uuid}")
    public VehicleDetailsDTO updateVehicleDetails(@PathVariable String uuid, @Valid @RequestBody final VehicleDetailsDTO vehicleDetailsDTO) {
        return vehicleService.updateVehicleDetails(uuid, vehicleDetailsDTO);
    }

    @PatchMapping("/{uuid}/instructors")
    public VehicleDetailsDTO updateVehicleInstructors(@PathVariable String uuid, @Valid @RequestBody final List<String> instructorUuids) {
        return vehicleService.updateVehicleInstructors(uuid, instructorUuids);
    }

    @PatchMapping("/{uuid}/trainees")
    public VehicleDetailsDTO updateVehicleTrainees(@PathVariable String uuid, @Valid @RequestBody final List<String> traineeUuids) {
        return vehicleService.updateVehicleTrainees(uuid, traineeUuids);
    }

    @PatchMapping("/{uuid}/sessions")
    public VehicleDetailsDTO updateVehicleSessions(@PathVariable String uuid, @Valid @RequestBody final List<String> sessionUuids) {
        return vehicleService.updateVehicleSessions(uuid, sessionUuids);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable String uuid) {
        vehicleService.deleteSession(uuid);
    }
}
