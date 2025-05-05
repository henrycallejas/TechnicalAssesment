package com.test.Technical.Assesment.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.test.Technical.Assesment.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PAYMENT_ORDER")
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENT_ORDER_ID")
    private Long paymentOrderId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    @JsonBackReference
    private Order order;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    @Enumerated(EnumType.STRING) // Guarda como texto (VARCHAR)
    @Column(nullable = false)
    private PaymentStatus status;

    public PaymentOrder() {
    }

    public PaymentOrder(Order order, BigDecimal amount, Date paymentDate, PaymentStatus status) {
        this.order = order;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public Long getPaymentOrderId() {
        return paymentOrderId;
    }

    public void setPaymentOrderId(Long paymentOrderId) {
        this.paymentOrderId = paymentOrderId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentdate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getStatus(){
        return this.status;
    }

    public void setStatus(PaymentStatus status){
        this.status = status;
    }
}
