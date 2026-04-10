package com.felixherder.ftotbe.instructors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
