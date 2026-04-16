package com.felixherder.ftotbe.users;

import java.util.List;

public interface UserService {
    List<UserDetailsDTO> getAll();

    UserDetailsDTO getByUuid(String uuid);
}
