package com.test.Technical.Assesment.service;

import java.util.List;
import java.util.Map;

import com.test.Technical.Assesment.model.PaymentOrder;
import com.test.Technical.Assesment.model.PaymentOrderDto;

public interface PaymentOrderService {
List<PaymentOrder> getAllPaymentOrders();
PaymentOrder getPaymentOrderById(Long id);
Map<String, Object> createPaymentOrder(PaymentOrderDto order);
PaymentOrder updatePaymentOrder(PaymentOrderDto order);
}
