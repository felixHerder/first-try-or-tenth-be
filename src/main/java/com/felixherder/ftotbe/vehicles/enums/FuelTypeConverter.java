package com.felixherder.ftotbe.vehicles.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FuelTypeConverter implements AttributeConverter<FuelType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(FuelType fuelType) {
        return fuelType.getId();
    }

    @Override
    public FuelType convertToEntityAttribute(Integer fuelTypeId) {
        return FuelType.fromId(fuelTypeId);
    }
}
