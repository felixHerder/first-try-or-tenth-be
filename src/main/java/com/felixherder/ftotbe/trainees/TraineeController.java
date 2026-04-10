package com.felixherder.ftotbe.trainees;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
