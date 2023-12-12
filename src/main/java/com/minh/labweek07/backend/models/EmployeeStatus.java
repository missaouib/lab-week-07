package com.minh.labweek07.backend.models;

import java.io.Serializable;

public enum EmployeeStatus implements Serializable {
    WORKING(1, "Đang làm việc"),
    ON_BREAK(0, "Tạm nghỉ"),
    RESIGNED(-1, "Nghỉ việc");

    private int code;
    private String description;

    EmployeeStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static EmployeeStatus getByCode(int code) {
        for (EmployeeStatus employeeStatus : values()) {
            if (employeeStatus.code == code) {
                return employeeStatus;
            }
        }
        throw new IllegalArgumentException("Invalid EmployeeStatus code: " + code);
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
