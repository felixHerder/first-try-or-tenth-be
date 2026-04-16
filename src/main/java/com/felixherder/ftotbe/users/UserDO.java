package com.felixherder.ftotbe.users;

import com.felixherder.ftotbe.common.BaseDO;
import com.felixherder.ftotbe.profiles.ProfileDO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE uuid=?")
@SQLRestriction("deleted = false")
public class UserDO extends BaseDO {
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_uuid")
    private ProfileDO profile;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}
