package com.felixherder.ftotbe.users;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDetailsDTO> getAll();

    UserDetailsDTO getByUuid(String uuid);

    UserDetailsDTO toDetailsDTO(UserDO userDO);
}
