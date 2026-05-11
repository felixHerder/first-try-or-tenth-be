package com.felixherder.ftotbe.vehicles.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

@Schema(
        extensions = {
                @Extension(properties = {
                        @ExtensionProperty(
                                name = "x-enum-varnames",
                                value = "[\"AUTOMATIC\", \"MANUAL\"]",
                                parseValue = true
                        )
                })
        }
)
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
