package com.felixherder.ftotbe.users;

import com.felixherder.ftotbe.profiles.ProfileDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserEditDTO(
        @Valid
        ProfileDTO profile,

        @Pattern(regexp = "^$|\\S+.*", message = "Username cannot be blank!")
        @Size(min = 5, message = "Username must be at least 5 characters long!")
        String username,

        @Pattern(regexp = "^$|\\S+.*", message = "Username cannot be blank!")
        @Size(min = 8, message = "Password must be at least 8 characters long.")
        String password,

        String role
) {
}
