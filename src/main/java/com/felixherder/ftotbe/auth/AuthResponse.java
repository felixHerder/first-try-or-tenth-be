package com.felixherder.ftotbe.auth;

import com.felixherder.ftotbe.users.UserDetailsDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AuthResponse(
        @NotNull
        String token,
        @NotNull
        UserDetailsDTO userDetails) {
}
