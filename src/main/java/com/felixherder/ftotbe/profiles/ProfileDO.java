package com.felixherder.ftotbe.profiles;

import com.felixherder.ftotbe.common.BaseDO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "profiles")
public class ProfileDO extends BaseDO {
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(nullable = false)
    private String name;

    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(nullable = false)
    private String phoneNumber;

    private String address;

    private String imageUrl;
}
