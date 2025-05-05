package com.test.Technical.Assesment.model;

import java.math.BigDecimal;
import java.util.Date;

import com.test.Technical.Assesment.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrderDto {
    private Long paymentOrderId;
    private Long orderId;
    private BigDecimal amount;
    private PaymentStatus status;
    private Date paymentDate;
    private String cardNumber;
    private String cvv;
    private Integer expiringMonth;
    private Integer expiringYear;
}
