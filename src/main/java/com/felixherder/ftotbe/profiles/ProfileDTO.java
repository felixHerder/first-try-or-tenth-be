package com.felixherder.ftotbe.profiles;

import lombok.*;

@Data
public class ProfileDTO {
    private String uuid;
    private String name;
    private String phoneNumber;
    private String address;
    private String imageUrl;
}
