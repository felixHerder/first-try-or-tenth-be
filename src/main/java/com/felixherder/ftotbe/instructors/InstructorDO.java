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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "instructors")
@SQLDelete(sql = "UPDATE instructors SET deleted = true WHERE uuid=?")
@SQLRestriction("deleted = false")
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

    @OneToMany(mappedBy = "instructor", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<TraineeDO> trainees = new LinkedHashSet<>();

    @OneToMany(mappedBy = "instructor", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<SessionDO> sessions = new LinkedHashSet<>();

    @PreRemove
    private void preRemove() {
        trainees.forEach(traineeDO -> traineeDO.setInstructor(null));
        sessions.forEach(sessionDO -> sessionDO.setInstructor(null));
    }

    public void addVehicle(VehicleDO vehicleDO) {
        vehicles.add(vehicleDO);
        vehicleDO.getInstructors().add(this);
    }

    public void removeVehicle(VehicleDO vehicleDO) {
        vehicles.remove(vehicleDO);
        vehicleDO.getInstructors().remove(this);
    }

    public void addTrainee(TraineeDO traineeDO) {
        trainees.add(traineeDO);
        traineeDO.setInstructor(this);
    }

    public void removeTrainee(TraineeDO traineeDO) {
        trainees.remove(traineeDO);
        traineeDO.setInstructor(null);
    }

    public void addSession(SessionDO sessionDO) {
        sessions.add(sessionDO);
        sessionDO.setInstructor(this);
    }

    public void removeSession(SessionDO sessionDO) {
        sessions.remove(sessionDO);
        sessionDO.setInstructor(null);
    }
}
