package com.test.Technical.Assesment.model;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ORDER_DETAIL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_DETAIL_ID")
    private Long orderDetailId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "AMOUNT")
    @NotBlank
    private Integer amount;

    @Column(name = "UNIT_PRICE")
    @NotBlank
    private BigDecimal unitPrice;

}