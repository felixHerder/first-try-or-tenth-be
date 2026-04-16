package com.felixherder.ftotbe.profiles;

import com.felixherder.ftotbe.common.BaseDO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "profiles")
@SQLDelete(sql = "UPDATE profiles SET deleted = true WHERE uuid=?")
@SQLRestriction("deleted = false")
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
