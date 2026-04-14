package com.felixherder.ftotbe.vehicles.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FuelType {
    GASOLINE(10),
    DIESEL(20),
    ELECTRIC(30);

    private final int id;

    @JsonValue
    public int getId() {
        return id;
    }

    @JsonCreator
    public static FuelType fromId(int id) {
        for (FuelType fuelType : FuelType.values()) {
            if (fuelType.getId() == id) {
                return fuelType;
            }
        }

        throw new IllegalArgumentException("No fuel type found with id: " + id);
    }
}
