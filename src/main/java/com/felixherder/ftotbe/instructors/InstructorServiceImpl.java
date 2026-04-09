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
    public List<InstructorDTO> getAll() {
        var instructors = instructorRepository.findAll();
        return instructorMapper.mapDoListToDtoList(instructors);
    }

    @Override
    public InstructorDTO getByUuid(String uuid) {
        return instructorRepository.findById(uuid)
                .map(instructorMapper::mapDoToDto)
                .orElseThrow(()-> new NotFoundException("Instructor with uuid: " + uuid + " not found!"));
    }
}
