package com.felixherder.ftotbe.trainees;

import com.felixherder.ftotbe.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeServiceImpl implements TraineeService {
    private final TraineeRepository traineeRepository;
    private final TraineeMapper traineeMapper;

    @Autowired
    public TraineeServiceImpl(TraineeRepository traineeRepository, TraineeMapper traineeMapper) {
        this.traineeRepository = traineeRepository;
        this.traineeMapper = traineeMapper;
    }

    @Override
    public List<TraineeSummaryDTO> getAll() {
        var trainees = traineeRepository.findAll();
        return trainees.stream().map(traineeMapper::toSummaryDto).toList();
    }

    @Override
    public TraineeDetailsDTO getByUuid(String uuid) {
        return traineeRepository.findById(uuid)
                .map(traineeMapper::toDetailsDto)
                .orElseThrow(()-> new NotFoundException("Trainee with uuid: " + uuid + " not found!"));
    }
}
