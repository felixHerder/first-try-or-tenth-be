package com.felixherder.ftotbe.profiles;

public record ProfileDTO(
        String uuid,
        String name,
        String phoneNumber,
        String address,
        String imageUrl
) {
}
