package com.felixherder.ftotbe.vehicles;

import lombok.Data;

import java.time.Year;

@Data
public class VehicleDTO {
    private String uuid;

    private String model;

    private String make;

    private Year year;

    private String licensePlate;

    private String color;

    private int engineTypeId;

    private int fuelTypeId;

    private int transmissionTypeId;

    private String imageUrl;
}
