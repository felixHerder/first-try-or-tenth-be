package com.felixherder.ftotbe.vehicles.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TransmissionType {
    AUTOMATIC(10),
    MANUAL(20);

    private final int id;

    @JsonValue
    public int getId() {
        return id;
    }

    @JsonCreator
    public static TransmissionType fromId(int id) {
        for (TransmissionType transmissionType : TransmissionType.values()) {
            if (transmissionType.getId() == id) {
                return transmissionType;
            }
        }

        throw new IllegalArgumentException("No transmission type found with id: " + id);
    }
}
