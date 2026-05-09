package com.felixherder.ftotbe.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProfileDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String uuid,

        @NotBlank(message = "Name is required!")
        @Size(min = 3, message = "Name must be at least 3 characters long!")
        String name,

        @NotBlank(message = "Phone number is required!")
        @Size(min = 9, message = "Phone must be at least 9 digits long!")
        String phoneNumber,

        String address,

        String imageUrl
) {
}
