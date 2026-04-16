package com.felixherder.ftotbe.users;

import com.felixherder.ftotbe.exceptions.NotFoundException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDetailsDTO> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDetailsDTO).toList();
    }

    @Override
    public UserDetailsDTO getByUuid(String uuid) {
        return userRepository.findById(uuid)
                .map(userMapper::toDetailsDTO)
                .orElseThrow(() -> new NotFoundException("User with uuid: " + uuid + " not found!"));
    }


    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User with username: " + username + " not found!"));
    }
}
