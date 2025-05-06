package com.test.Technical.Assesment.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.test.Technical.Assesment.enums.PaymentStatus;
import com.test.Technical.Assesment.model.PaymentOrder;

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
    private Integer amount;
    private BigDecimal totalAmount;
    private BigDecimal unitPrice;
    private PaymentStatus status;
    private Date paymentDate;
    private String cardNumber;
    private String cvv;
    private Integer expiringMonth;
    private Integer expiringYear;
    private List<OrderDetailDto> details;
}
