package com.felixherder.ftotbe.instructors;

import com.felixherder.ftotbe.common.BaseDO;
import com.felixherder.ftotbe.profiles.ProfileDO;
import com.felixherder.ftotbe.sessions.SessionDO;
import com.felixherder.ftotbe.trainees.TraineeDO;
import com.felixherder.ftotbe.vehicles.VehicleDO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "instructors")
public class InstructorDO extends BaseDO {
    @EqualsAndHashCode.Include
    @ToString.Include
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_uuid")
    private ProfileDO profile;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "instructors_vehicles",
            joinColumns = @JoinColumn(name = "instructor_uuid"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_uuid")
    )
    private Set<VehicleDO> vehicles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "instructor")
    private Set<TraineeDO> trainees = new LinkedHashSet<>();

    @OneToMany(mappedBy = "instructor")
    private Set<SessionDO> sessions = new LinkedHashSet<>();
}
