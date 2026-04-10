package com.felixherder.ftotbe.vehicles;

import com.felixherder.ftotbe.common.BaseDO;
import com.felixherder.ftotbe.instructors.InstructorDO;
import com.felixherder.ftotbe.sessions.SessionDO;
import com.felixherder.ftotbe.trainees.TraineeDO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Year;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity(name = "vehicles")
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
    private int engineTypeId;

    @Column(nullable = false)
    private int fuelTypeId;

    @Column(nullable = false)
    private int transmissionTypeId;

    private String imageUrl;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "vehicles")
    private Set<InstructorDO> instructors = new LinkedHashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TraineeDO> trainees = new LinkedHashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "vehicle")
    private Set<SessionDO> session = new LinkedHashSet<>();

}
