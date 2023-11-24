package com.minh.labweek07.backend.enums;

public enum Size {
    S,M,L,XL,XXL,XXXL;

    public static Size getColorFromInt(int size) {
        if (size >= 0 && size < Color.values().length) {
            return Size.values()[size];
        } else {
            // Xử lý trường hợp ngoại lệ hoặc giá trị không hợp lệ
            throw new IllegalArgumentException("Giá trị không hợp lệ cho Color Enum: " + size);
        }
    }

    public static Size getSizeFromInt(int size) {
        if (size >= 0 && size < Size.values().length) {
            return Size.values()[size];
        } else {
            // Xử lý trường hợp ngoại lệ hoặc giá trị không hợp lệ
            throw new IllegalArgumentException("Giá trị không hợp lệ cho Size Enum: " + size);
        }
    }
}
