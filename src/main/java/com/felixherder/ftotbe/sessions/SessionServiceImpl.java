package com.felixherder.ftotbe.sessions;

import com.felixherder.ftotbe.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    public SessionServiceImpl(SessionRepository sessionRepository, SessionMapper sessionMapper) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
    }

    @Override
    public List<SessionSummaryDTO> getAll() {
        var sessions = sessionRepository.findAll();
        return sessions.stream().map(sessionMapper::toSummaryDto).toList();
    }

    @Override
    public SessionDetailsDTO getByUuid(String uuid) {
        return sessionRepository.findById(uuid)
                .map(sessionMapper::toDetailsDto)
                .orElseThrow(() -> new NotFoundException("Session with uuid: " + uuid + " not found!"));
    }
}
