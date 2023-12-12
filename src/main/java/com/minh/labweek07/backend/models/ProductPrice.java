package com.minh.labweek07.backend.models;

import com.minh.labweek07.backend.pk.ProductPricePK;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "product_price")
@NamedQuery(name = "ProductPrice.findByProductID", query = "SELECT p.price FROM ProductPrice p WHERE p.product.productId = :productID ORDER BY p.priceDate DESC")
@IdClass(ProductPricePK.class)
public class ProductPrice implements Serializable {
    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productID")
    private Product product;
    @Id
    @Column
    private Date priceDate;
    @Column
    private double price;
    @Column
    private String note;

    public ProductPrice(Product product, Date priceDate, String note, double price) {
        this.product = product;
        this.priceDate = priceDate;
        this.price = price;
        this.note = note;
    }

    public ProductPrice() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
