package com.felixherder.ftotbe.sessions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/sessions")
public class SessionController {
    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<SessionSummaryDTO> getAll() {
        return sessionService.getAll();
    }

    @GetMapping("/{uuid}")
    public SessionDetailsDTO getDetails(@PathVariable String uuid) {
        return sessionService.getByUuid(uuid);
    }

}
