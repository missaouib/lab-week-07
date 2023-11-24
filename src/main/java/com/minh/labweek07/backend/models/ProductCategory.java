package com.minh.labweek07.backend.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product_category")
public class ProductCategory {
    @Id
    private long id;
    @Column(name = "category_name")
    private String categoryName;


    public ProductCategory(long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;

    }

    public ProductCategory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }




}
