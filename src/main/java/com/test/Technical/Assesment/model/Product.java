package com.test.Technical.Assesment.model;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "NAME", length = 200)
    private String name;

    @Column(name = "DESCRIPTION", length = 500, columnDefinition = "TEXT")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "image", length = 200)
    private String image;

    @Column(name = "CATEGORY")
    private Integer category;

    // Constructors, Getters and Setters

    public Product() {
    }

    public Product(String name, String description, BigDecimal price, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
