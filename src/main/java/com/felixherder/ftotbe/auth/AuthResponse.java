package com.felixherder.ftotbe.auth;

import com.felixherder.ftotbe.users.UserDetailsDTO;
import lombok.Builder;

@Builder
public record AuthResponse(String token, UserDetailsDTO userDetails) {
}
