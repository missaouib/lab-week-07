package com.minh.labweek07.backend.models;


import com.minh.labweek07.backend.converter.ColorConverter;
import com.minh.labweek07.backend.converter.SizeConverter;
import com.minh.labweek07.backend.enums.Color;
import com.minh.labweek07.backend.enums.Size;
import com.minh.labweek07.backend.pk.ProductDetailPK;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_detail")
@Data
@Getter
@Setter
public class ProductDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Convert(converter = ColorConverter.class)
    private Color color;

    @Convert(converter = SizeConverter.class)
    private Size size;
    @Column
    private int quantity;
    @OneToMany(mappedBy = "productDetail",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails=new ArrayList<OrderDetail>();

    public ProductDetail() {
    }

    public ProductDetail( Product product, Color color, Size id, int quantity) {

        this.product = product;
        this.color = color;
        this.size = id;
        this.quantity = quantity;
    }



    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public ProductDetail( Product product, Color color, Size size, int quantity, List<OrderDetail> orderDetails) {

        this.product = product;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.orderDetails = orderDetails;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
