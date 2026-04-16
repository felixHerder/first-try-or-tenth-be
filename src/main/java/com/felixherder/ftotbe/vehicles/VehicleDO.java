package com.felixherder.ftotbe.vehicles;

import com.felixherder.ftotbe.common.BaseDO;
import com.felixherder.ftotbe.instructors.InstructorDO;
import com.felixherder.ftotbe.sessions.SessionDO;
import com.felixherder.ftotbe.trainees.TraineeDO;
import com.felixherder.ftotbe.vehicles.enums.EngineType;
import com.felixherder.ftotbe.vehicles.enums.FuelType;
import com.felixherder.ftotbe.vehicles.enums.TransmissionType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.Year;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity(name = "vehicles")
@SQLDelete(sql = "UPDATE vehicles SET deleted = true WHERE uuid=?")
@SQLRestriction("deleted = false")
public class VehicleDO extends BaseDO {
    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private Year year;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private EngineType engineType;

    @Column(nullable = false)
    private FuelType fuelType;

    @Column(nullable = false)
    private TransmissionType transmissionType;

    private String imageUrl;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "vehicles")
    private Set<InstructorDO> instructors = new LinkedHashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private Set<TraineeDO> trainees = new LinkedHashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private Set<SessionDO> sessions = new LinkedHashSet<>();

    public void addInstructor(InstructorDO instructorDO) {
        instructors.add(instructorDO);
        instructorDO.getVehicles().add(this);
    }

    public void removeInstructor(InstructorDO instructorDO) {
        instructors.remove(instructorDO);
        instructorDO.getVehicles().remove(this);
    }

    public void addTrainee(TraineeDO traineeDO) {
        trainees.add(traineeDO);
        traineeDO.setVehicle(this);
    }

    public void removeTrainee(TraineeDO traineeDO) {
        trainees.remove(traineeDO);
        traineeDO.setVehicle(null);
    }

    public void addSession(SessionDO sessionDO) {
        sessions.add(sessionDO);
        sessionDO.setVehicle(this);
    }

    public void removeSession(SessionDO sessionDO) {
        sessions.remove(sessionDO);
        sessionDO.setVehicle(null);
    }

}
