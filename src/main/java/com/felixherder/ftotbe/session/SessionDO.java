package com.felixherder.ftotbe.session;

import com.felixherder.ftotbe.instructors.InstructorDO;
import com.felixherder.ftotbe.common.BaseDO;
import com.felixherder.ftotbe.trainees.TraineeDO;
import com.felixherder.ftotbe.vehicles.VehicleDO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "sessions")
public class SessionDO extends BaseDO {

    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(nullable = false)
    private ZonedDateTime scheduledAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private TraineeDO trainee;

    @ManyToOne(fetch = FetchType.LAZY)
    private InstructorDO instructor;

    @ManyToOne(fetch = FetchType.LAZY)
    private VehicleDO vehicle;

}
