package com.felixherder.ftotbe.vehicles.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EngineTypeConverter implements AttributeConverter<EngineType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(EngineType engineType) {
        return engineType.getId();
    }

    @Override
    public EngineType convertToEntityAttribute(Integer engineTypeId) {
        return EngineType.fromId(engineTypeId);
    }
}
