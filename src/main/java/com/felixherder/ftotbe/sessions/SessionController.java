package com.felixherder.ftotbe.sessions;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionDetailsDTO createSession(@Valid @RequestBody SessionCreateDTO sessionCreateDTO) {
        return sessionService.createSession(sessionCreateDTO);
    }

    @PatchMapping("/{uuid}")
    public SessionDetailsDTO editSession(@PathVariable String uuid, @Valid @RequestBody SessionEditDTO sessionEditDTO) {
        return sessionService.editSession(uuid, sessionEditDTO);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSession(@PathVariable String uuid) {
        sessionService.deleteSession(uuid);
    }


}
