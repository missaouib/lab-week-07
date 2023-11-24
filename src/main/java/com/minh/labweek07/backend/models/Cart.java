package com.minh.labweek07.backend.models;

import com.minh.labweek07.backend.enums.Color;
import com.minh.labweek07.backend.enums.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    private long id;
    private String name;
    private String image;
    private double price;
    private Color color;
    private Size size;
    private int quantity;


}
