package com.test.Technical.Assesment.service;

import java.util.List;

import com.test.Technical.Assesment.dto.OrderDetailDto;
import com.test.Technical.Assesment.model.Order;
import com.test.Technical.Assesment.model.OrderDetail;

public interface OrderService {
List<Order> getAllOrders();
Order getOrderById(Long id);
OrderDetail createOrder(OrderDetailDto order);
}
