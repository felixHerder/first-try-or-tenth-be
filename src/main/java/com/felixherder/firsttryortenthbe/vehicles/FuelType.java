package com.felixherder.firsttryortenthbe.vehicles;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FuelType {
    GASOLINE(10),
    DIESEL(20),
    ELECTRIC(30);

    private final int id;
}
