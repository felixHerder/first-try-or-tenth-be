package com.felixherder.ftotbe.sessions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionDO, String> {
}
