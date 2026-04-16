package com.felixherder.ftotbe.users;

import com.felixherder.ftotbe.profiles.ProfileDTO;

public record UserDetailsDTO(
        String uuid,
        ProfileDTO profile,
        String username
) {
}
