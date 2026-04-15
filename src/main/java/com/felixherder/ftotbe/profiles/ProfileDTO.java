package com.felixherder.ftotbe.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record ProfileDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String uuid,

        @NotBlank(message = "Name is required!")
        String name,

        @NotBlank(message = "Phone number is required!")
        String phoneNumber,

        String address,
        
        String imageUrl
) {
}
