package com.felixherder.ftotbe.sessions;

import java.util.List;

public interface SessionService {
    List<SessionSummaryDTO> getAll();

    SessionDetailsDTO getByUuid(String uuid);

    SessionDetailsDTO createSession(SessionCreateDTO sessionCreateDTO);

    SessionDetailsDTO editSession(String uuid, SessionEditDTO sessionEditDTO);

    void deleteSession(String uuid);
}
