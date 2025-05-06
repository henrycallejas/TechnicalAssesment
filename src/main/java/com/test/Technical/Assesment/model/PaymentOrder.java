package com.test.Technical.Assesment.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.test.Technical.Assesment.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PAYMENT_ORDER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENT_ORDER_ID")
    private Long paymentOrderId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    @JsonBackReference
    private Order order;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    @Enumerated(EnumType.STRING) // Guarda como texto (VARCHAR)
    @Column(nullable = false)
    private PaymentStatus status;

}
