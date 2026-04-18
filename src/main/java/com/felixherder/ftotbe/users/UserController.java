package com.felixherder.ftotbe.users;

import com.felixherder.ftotbe.auth.AuthRequest;
import com.felixherder.ftotbe.auth.AuthResponse;
import com.felixherder.ftotbe.auth.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping
    public List<UserDetailsDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{uuid}")
    public UserDetailsDTO getByUuid(@PathVariable String uuid) {
        return userService.getByUuid(uuid);
    }

    @PostMapping("/login")
    public AuthResponse loginUser(@RequestBody @Valid AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
        );
        var userDetails = userService.loadUserByUsername(authRequest.username());
        String jwtToken = jwtService.generateToken(userDetails);

        return AuthResponse.builder()
                .token(jwtToken)
                .userDetails(userService.toDetailsDTO((UserDO) userDetails))
                .build();
    }
}
