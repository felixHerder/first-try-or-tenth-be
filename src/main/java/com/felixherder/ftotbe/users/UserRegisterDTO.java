package com.felixherder.ftotbe.users;

import com.felixherder.ftotbe.profiles.ProfileDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
        ProfileDTO profile,

        @NotBlank(message = "Username is required!")
        @Size(min = 5, message = "Username must be at least 5 characters long!")
        String username,

        @NotBlank(message = "Password is required!")
        @Size(min = 8, message = "Password must be at least 8 characters long!")
        String password,

        String role
) {
}
