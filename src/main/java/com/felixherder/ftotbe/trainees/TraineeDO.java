package com.felixherder.ftotbe.trainees;

import com.felixherder.ftotbe.instructors.InstructorDO;
import com.felixherder.ftotbe.profiles.ProfileDO;
import com.felixherder.ftotbe.session.SessionDO;
import com.felixherder.ftotbe.common.BaseDO;
import com.felixherder.ftotbe.vehicles.VehicleDO;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "trainees")
public class TraineeDO extends BaseDO {
    @EqualsAndHashCode.Include
    @ToString.Include
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="profile_uuid")
    private ProfileDO profile;

    @ManyToOne(fetch = FetchType.LAZY)
    private InstructorDO instructor;

    @ManyToOne(fetch = FetchType.LAZY)
    private VehicleDO vehicle;

    @OneToMany(mappedBy = "trainee")
    private final Set<SessionDO> session = new LinkedHashSet<>();
}

