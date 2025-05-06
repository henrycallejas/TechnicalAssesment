package com.test.Technical.Assesment.service;

import java.util.List;
import java.util.Map;

import com.test.Technical.Assesment.dto.PaymentOrderDto;
import com.test.Technical.Assesment.model.PaymentOrder;

public interface PaymentOrderService {
List<PaymentOrder> getAllPaymentOrders();
PaymentOrder getPaymentOrderById(Long id);
Map<String, Object> processPayment(Long clientId, PaymentOrderDto order);
PaymentOrder updatePaymentOrder(PaymentOrderDto order);
}
