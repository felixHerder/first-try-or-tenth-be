package com.felixherder.ftotbe.vehicles.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EngineType {
    INTERNAL_COMBUSTION(10),
    HYBRID(20),
    ELECTRIC(30);

    private final int id;

    @JsonValue
    public int getId() {
        return id;
    }

    @JsonCreator
    public static EngineType fromId(int id) {
        for (EngineType engineType : EngineType.values()) {
            if (engineType.getId() == id) {
                return engineType;
            }
        }
        throw new IllegalArgumentException(("No engine type found with id: " + id));
    }
}
