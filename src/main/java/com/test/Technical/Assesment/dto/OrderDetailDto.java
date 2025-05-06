package com.test.Technical.Assesment.dto;
import java.math.BigDecimal;

import com.test.Technical.Assesment.model.Order;
import com.test.Technical.Assesment.model.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailDto {
    private Long clientId;
    private Long productId;
    private Integer amount;
    private BigDecimal unitPrice;
    private Order order;
    private OrderDetail orderDetail;
}
