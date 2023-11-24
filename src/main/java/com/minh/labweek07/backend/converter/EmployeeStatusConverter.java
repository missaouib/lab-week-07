package com.minh.labweek07.backend.converter;


import com.minh.labweek07.backend.models.EmployeeStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class EmployeeStatusConverter implements AttributeConverter<EmployeeStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(EmployeeStatus attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public EmployeeStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(EmployeeStatus.values())
                .filter(c -> c.getCode() == dbData)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
