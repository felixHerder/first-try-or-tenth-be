package com.felixherder.ftotbe.instructors;

import com.felixherder.ftotbe.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository, InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
    }

    @Override
    public List<InstructorSummaryDTO> getAll() {
        var instructors = instructorRepository.findAll();
        return instructors.stream().map(instructorMapper::toSummaryDto).toList();
    }

    @Override
    public InstructorDetailsDTO getByUuid(String uuid) {
        return instructorRepository.findById(uuid)
                .map(instructorMapper::toDetailsDto)
                .orElseThrow(()-> new NotFoundException("Instructor with uuid: " + uuid + " not found!"));
    }
}
