package com.felixherder.ftotbe.trainees;

import java.util.List;

public interface TraineeService {
    List<TraineeSummaryDTO> getAll();

    TraineeDetailsDTO getByUuid(String uuid);

    TraineeDetailsDTO createTrainee(TraineeDetailsDTO traineeDetailsDTO);

    TraineeDetailsDTO updateTraineeProfile(String uuid, TraineeDetailsDTO traineeDetailsDTO);

    TraineeDetailsDTO updateTraineeVehicle(String uuid, String vehicleUuid);

    TraineeDetailsDTO updateTraineeInstructor(String uuid, String instructorUuid);

    TraineeDetailsDTO updateTraineeSessions(String uuid, List<String> sessionUuids);
}
