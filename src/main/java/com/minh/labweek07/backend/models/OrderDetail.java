package com.minh.labweek07.backend.models;

import com.minh.labweek07.backend.pk.OrderDetailPK;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "order_details")
@IdClass(OrderDetailPK.class)
public class OrderDetail implements Serializable {
    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderID")
    private Order order;
    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productDetailId")
    private ProductDetail productDetail;
    @Column
    private long quantity;
    @Column
    private double price;
    @Column
    private String note;

    public OrderDetail(Order order, ProductDetail product, long quantity, double price, String note) {
        this.order = order;
        this.productDetail = product;
        this.quantity = quantity;
        this.price = price;
        this.note = note;
    }

    public OrderDetail() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductDetail getProduct() {
        return productDetail;
    }

    public void setProduct(ProductDetail product) {
        this.productDetail = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
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

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }
}
