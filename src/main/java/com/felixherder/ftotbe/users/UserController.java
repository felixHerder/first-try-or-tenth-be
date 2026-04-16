package com.felixherder.ftotbe.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDetailsDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{uuid}")
    public UserDetailsDTO getByUuid(@PathVariable String uuid) {
        return userService.getByUuid(uuid);
    }
}
