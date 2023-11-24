package com.minh.labweek07.backend.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "product_image")
public class ProductImage implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long image_id;
    @Column
    private String path;
    @Column
    private String alternavtive;

    public ProductImage(Product product, long image_id, String path, String alternavtive) {
        this.product = product;
        this.image_id = image_id;
        this.path = path;
        this.alternavtive = alternavtive;
    }

    public ProductImage() {
    }

    public Product getProduct() {
        return product;
    }

    public long getImage_id() {
        return image_id;
    }

    public String getPath() {
        return path;
    }

    public String getAlternavtive() {
        return alternavtive;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setImage_id(long image_id) {
        this.image_id = image_id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setAlternavtive(String alternavtive) {
        this.alternavtive = alternavtive;
    }
}
