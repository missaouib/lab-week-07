package com.minh.labweek07.backend.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;


public enum Color{
    RED,GREEN,BLUE,YELLOW,BLACK,BROWN,WHITE,ORANGE,PINK,PURPLE,GREY;
    public static Color getColorFromInt(int colorOrdinal) {
        if (colorOrdinal >= 0 && colorOrdinal < Color.values().length) {
            return Color.values()[colorOrdinal];
        } else {
            // Xử lý trường hợp ngoại lệ hoặc giá trị không hợp lệ
            throw new IllegalArgumentException("Giá trị không hợp lệ cho Color Enum: " + colorOrdinal);
        }
    }


    }

