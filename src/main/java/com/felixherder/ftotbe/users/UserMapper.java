package com.felixherder.ftotbe.users;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDetailsDTO toDetailsDTO(UserDO userDO);
}
