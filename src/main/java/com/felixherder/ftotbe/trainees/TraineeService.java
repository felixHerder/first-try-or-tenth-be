package com.felixherder.ftotbe.trainees;

import java.util.List;

public interface TraineeService {
    List<TraineeSummaryDTO> getAll();
    TraineeDetailsDTO getByUuid(String uuid);
}
