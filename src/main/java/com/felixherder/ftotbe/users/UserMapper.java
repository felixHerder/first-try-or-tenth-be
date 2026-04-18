package com.felixherder.ftotbe.users;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface UserMapper {
    UserDetailsDTO toDetailsDTO(UserDO userDO);

    UserDO toDO(UserRegisterDTO userRegisterDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDoFromDto(UserEditDTO userEditDTO, @MappingTarget UserDO userDO);
}
