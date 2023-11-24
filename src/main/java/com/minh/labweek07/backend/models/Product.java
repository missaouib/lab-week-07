package com.minh.labweek07.backend.models;

import com.minh.labweek07.backend.converter.ProductStatusConverter;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@NamedQuery(name = "Product.selectAll", query = "SELECT p FROM Product p")
@NamedQuery(name = "Product.selectByID", query = "SELECT p FROM Product p WHERE p.productId = :productID")
@NamedQuery(name = "Product.getProductByStatus", query = "select  p From Product p where p.status = :status ")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @Column(columnDefinition = "nvarchar(255)")
    private String name;
    @Column(columnDefinition = "nvarchar(255)")

    private String description;
    @Column(columnDefinition = "nvarchar(255)")

    private String unit;
    @Column(columnDefinition = "nvarchar(255)")

    private String manufacturerName;
    @Convert(converter = ProductStatusConverter.class)
    private ProductStatus status;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductPrice> prices = new ArrayList<ProductPrice>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ProductImage> productImages=new ArrayList<ProductImage>();
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductDetail> productDetails= new ArrayList<ProductDetail>();


    public Product(long productId, String name, String description, String unit, String manufacturerName, ProductStatus status, ProductCategory productCategory) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.manufacturerName = manufacturerName;
        this.status = status;
        this.productCategory = productCategory;
    }

    public Product(String name, String description, String unit, String manufacturerName, ProductStatus status, List<ProductPrice> prices, ProductCategory productCategory) {
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.manufacturerName = manufacturerName;
        this.status = status;
        this.prices = prices;
        this.productCategory = productCategory;

    }

    public Product(long productId, String name, String description, String unit, String manufacturerName, ProductStatus status, List<ProductPrice> prices, List<ProductImage> productImages, ProductCategory productCategory, List<ProductDetail> productDetails) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.manufacturerName = manufacturerName;
        this.status = status;
        this.prices = prices;
        this.productImages = productImages;
        this.productCategory = productCategory;
        this.productDetails = productDetails;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }


    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public Product() {
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public List<ProductPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<ProductPrice> prices) {
        this.prices = prices;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }
}
