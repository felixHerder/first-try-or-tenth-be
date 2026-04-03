package com.felixherder.firsttryortenthbe.vehicles;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EngineType {
    INTERNAL_COMBUSTION(10),
    HYBRID(20),
    ELECTRIC(30);

    private final int id;
}
