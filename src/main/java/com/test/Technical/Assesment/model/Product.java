package com.test.Technical.Assesment.model;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "NAME", length = 200)
    @NotBlank
    private String name;

    @Column(name = "DESCRIPTION", length = 500, columnDefinition = "TEXT")
    private String description;

    @Column(name = "PRICE")
    @NotBlank
    private BigDecimal price;

    @Column(name = "image", length = 200)
    private String image;

    @Column(name = "CATEGORY")
    private Integer category;

}
