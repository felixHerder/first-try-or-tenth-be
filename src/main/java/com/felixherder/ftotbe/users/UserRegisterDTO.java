package com.felixherder.ftotbe.users;

import com.felixherder.ftotbe.profiles.ProfileDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
        ProfileDTO profile,

        @NotBlank(message = "Username is required!")
        @Size(min = 5, message = "Username should have at least 5 characters!")
        String username,

        @NotBlank(message = "Password is required!")
        @Size(min = 8, message = "Password should have at least 8 characters!")
        String password,

        String role
) {
}
