package com.felixherder.ftotbe.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDO, String> {
    Optional<UserDO> findByUsername(String username);
}
