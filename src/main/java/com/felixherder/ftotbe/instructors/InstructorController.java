package com.felixherder.ftotbe.instructors;

import com.felixherder.ftotbe.profiles.ProfileDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping()
    public List<InstructorSummaryDTO> getAll() {
        return instructorService.getAll();
    }

    @GetMapping("/{uuid}")
    public InstructorDetailsDTO getDetails(@PathVariable String uuid) {
        return instructorService.getByUuid(uuid);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public InstructorDetailsDTO createInstructor(@Valid @RequestBody InstructorDetailsDTO instructorDetailsDTO) {
        return instructorService.createInstructor(instructorDetailsDTO);
    }

    @PutMapping("/{uuid}/profile")
    public InstructorDetailsDTO updateInstructorProfile(@PathVariable String uuid,
                                                        @Valid @RequestBody ProfileDTO profileDto) {
        return instructorService.updateInstructorProfile(uuid, profileDto);
    }

    @PatchMapping("/{uuid}/vehicles")
    @Validated
    public InstructorDetailsDTO updateInstructorVehicles(@PathVariable String uuid,
                                                         @NotNull(message = "Array of vehicle uuids is required!")
                                                         @RequestBody List<String> vehicleUuids) {
        return instructorService.updateInstructorVehicles(uuid, vehicleUuids);
    }

    @PatchMapping("/{uuid}/trainees")
    @Validated
    public InstructorDetailsDTO updateInstructorTrainees(@PathVariable String uuid,
                                                         @NotNull(message = "Array of trainee uuids is required!")
                                                         @RequestBody List<String> traineeUuids) {
        return instructorService.updateInstructorTrainees(uuid, traineeUuids);
    }

    @PatchMapping("/{uuid}/sessions")
    @Validated
    public InstructorDetailsDTO updateInstructorSessions(@PathVariable String uuid,
                                                         @NotNull(message = "Array of session uuids is required!")
                                                         @RequestBody List<String> sessionUuids) {
        return instructorService.updateInstructorSessions(uuid, sessionUuids);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInstructor(@PathVariable String uuid) {
        instructorService.deleteInstructor(uuid);
    }
}
