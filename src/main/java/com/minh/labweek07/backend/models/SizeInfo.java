package com.minh.labweek07.backend.models;

public class SizeInfo {
    private int id;
    private String name;

    public SizeInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

