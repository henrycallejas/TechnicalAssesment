package com.test.Technical.Assesment.model;
import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ORDER_DETAIL")
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
    private Integer amount;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    // Constructors, Getters and Setters

    public OrderDetail() {
    }

    public OrderDetail(Order order, Product product, Integer amount, BigDecimal unitPrice) {
        this.order = order;
        this.product = product;
        this.amount = amount;
        this.unitPrice = unitPrice;
    }

    public Long getOrderDetail() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}