package com.felixherder.ftotbe.users;

import com.felixherder.ftotbe.common.BaseDO;
import com.felixherder.ftotbe.profiles.ProfileDO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity(name = "users")
public class UsersDO extends BaseDO {
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="profile_uuid")
    private ProfileDO profile;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}
