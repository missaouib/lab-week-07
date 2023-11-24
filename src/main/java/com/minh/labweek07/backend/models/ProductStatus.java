package com.minh.labweek07.backend.models;

public enum ProductStatus {
    ACTIVE(1, "Đang kinh doanh"),
    SUSPENDED(0, "Tạm ngưng"),
    DISCONTINUED(-1, "Không kinh doanh nữa");

    private int code;
    private String description;

    ProductStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ProductStatus getByCode(int code) {
        for (ProductStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid ProductStatus code: " + code);
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
