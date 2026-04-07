package com.felixherder.ftotbe.vehicles;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TransmissionType {
    AUTOMATIC(10),
    MANUAL(20);

    private final int id;
}
