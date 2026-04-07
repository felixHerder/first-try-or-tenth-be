package com.felixherder.ftotbe.vehicles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


import java.time.Year;

import static jakarta.persistence.GenerationType.UUID;

@Data
@Entity(name = "vehicles")
public class VehicleDO {
    @Id
    @GeneratedValue(strategy = UUID)
    private String uuid;

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
}
