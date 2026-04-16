package com.felixherder.ftotbe.trainees;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/trainees")
public class TraineeController {
    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping
    public List<TraineeSummaryDTO> getAll() {
        return traineeService.getAll();
    }

    @GetMapping("/{uuid}")
    public TraineeDetailsDTO getDetails(@PathVariable String uuid) {
        return traineeService.getByUuid(uuid);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TraineeDetailsDTO createTrainee(@Valid @RequestBody TraineeDetailsDTO traineeDetailsDTO) {
        return traineeService.createTrainee(traineeDetailsDTO);
    }

    @PutMapping("/{uuid}/profile")
    public TraineeDetailsDTO updateTraineeProfile(@PathVariable String uuid,
                                                  @Valid @RequestBody TraineeDetailsDTO traineeDetailsDTO) {
        return traineeService.updateTraineeProfile(uuid, traineeDetailsDTO);
    }

    @PatchMapping("/{uuid}/vehicle")
    @Validated
    public TraineeDetailsDTO updateTraineeVehicle(@PathVariable String uuid,
                                                  @NotBlank(message = "Vehicle uuid is required!")
                                                  @RequestBody String vehicleUuid) {
        return traineeService.updateTraineeVehicle(uuid, vehicleUuid);
    }

    @PatchMapping("/{uuid}/instructor")
    @Validated
    public TraineeDetailsDTO updateTraineeInstructor(@PathVariable String uuid,
                                                     @NotBlank(message = "Trainee uuid is required!")
                                                     @RequestBody String instructorUuid) {
        return traineeService.updateTraineeInstructor(uuid, instructorUuid);
    }

    @PatchMapping("/{uuid}/sessions")
    @Validated
    public TraineeDetailsDTO updateTraineeSessions(@PathVariable String uuid,
                                                   @NotNull(message = "Array of session uuids is required!")
                                                   @RequestBody List<String> sessionUuids) {
        return traineeService.updateTraineeSessions(uuid, sessionUuids);
    }

}
