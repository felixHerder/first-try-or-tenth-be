package com.felixherder.ftotbe.vehicles.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TransmissionTypeConverter implements AttributeConverter<TransmissionType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TransmissionType transmissionType) {
        return transmissionType.getId();
    }

    @Override
    public TransmissionType convertToEntityAttribute(Integer transmissionTypeId) {
        return TransmissionType.fromId(transmissionTypeId);
    }
}
