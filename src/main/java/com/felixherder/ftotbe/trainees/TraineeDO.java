package com.felixherder.ftotbe.trainees;

import com.felixherder.ftotbe.common.BaseDO;
import com.felixherder.ftotbe.instructors.InstructorDO;
import com.felixherder.ftotbe.profiles.ProfileDO;
import com.felixherder.ftotbe.sessions.SessionDO;
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
@Entity(name = "trainees")
public class TraineeDO extends BaseDO {
    @EqualsAndHashCode.Include
    @ToString.Include
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_uuid")
    private ProfileDO profile;

    @ManyToOne(fetch = FetchType.LAZY)
    private InstructorDO instructor;

    @ManyToOne(fetch = FetchType.LAZY)
    private VehicleDO vehicle;

    @OneToMany(mappedBy = "trainee", cascade = CascadeType.ALL)
    private Set<SessionDO> sessions = new LinkedHashSet<>();

    public void addSession(SessionDO sessionDO) {
        sessions.add(sessionDO);
        sessionDO.setTrainee(this);
    }

    public void removeSession(SessionDO sessionDO) {
        sessions.remove(sessionDO);
        sessionDO.setTrainee(null);
    }
}

