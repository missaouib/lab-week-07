package com.minh.labweek07.backend.converter;


import com.minh.labweek07.backend.enums.Color;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;
@Converter(autoApply = true)
public class ColorConverter implements AttributeConverter<Color, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Color attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.ordinal();
    }

    @Override
    public Color convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(Color.values())
                .filter(c -> c.ordinal() == dbData)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
