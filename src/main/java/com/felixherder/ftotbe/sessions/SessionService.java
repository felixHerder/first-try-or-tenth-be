package com.felixherder.ftotbe.sessions;

import java.util.List;

public interface SessionService {
    List<SessionSummaryDTO> getAll();

    SessionDetailsDTO getByUuid(String uuid);
}
