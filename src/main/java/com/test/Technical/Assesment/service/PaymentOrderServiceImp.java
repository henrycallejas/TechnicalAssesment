package com.test.Technical.Assesment.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Technical.Assesment.enums.PaymentStatus;
import com.test.Technical.Assesment.model.Order;
import com.test.Technical.Assesment.model.PaymentOrder;
import com.test.Technical.Assesment.model.PaymentOrderDto;
import com.test.Technical.Assesment.repository.OrderRepository;
import com.test.Technical.Assesment.repository.PaymentOrderRepository;
import com.test.Technical.Assesment.utils.Validator;

@Service
public class PaymentOrderServiceImp implements PaymentOrderService {

    @Autowired
    PaymentOrderRepository paymentOrderRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<PaymentOrder> getAllPaymentOrders() {
        return this.paymentOrderRepository.findAll();
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) {
        Optional<PaymentOrder> paymentOrder = this.paymentOrderRepository.findById(id);
        return paymentOrder.orElse(null);
    }

    @Override
    public Map<String, Object> createPaymentOrder(PaymentOrderDto order) {
        String expiringmonth = "";
        Map<String, Object> response = new HashMap<>();
        if(order.getExpiringMonth() <= 9){
            expiringmonth = "0" + order.getExpiringMonth().toString() + "/";
        }else{
            expiringmonth = order.getExpiringMonth().toString();
        }
        String expiringDate = expiringmonth + order.getExpiringYear();
        if (Validator.isCardNumberValid(order.getCardNumber()) && Validator.isExpiringDateValid(expiringDate)) {
            PaymentOrder newOrder = new PaymentOrder();
            newOrder.setAmount(order.getAmount());
            newOrder.setPaymentDate(new Date()); //POSIBLE QUITAR
            newOrder.setStatus(PaymentStatus.APROBADO);
            Order orderFound = this.orderRepository.findById(order.getOrderId())
            .orElse(null);
            newOrder.setOrder(orderFound);
            response.put("response", this.paymentOrderRepository.save(newOrder));
            return response;
        }
        if(!Validator.isCardNumberValid(order.getCardNumber())){
            response.put("response", "Número de tarjeta no válido.");
            return response;
        }else if(!Validator.isExpiringDateValid(expiringDate)){
            response.put("response", "Fecha de expiración no válida.");
            return response;
        }
        return response;
    }

    @Override
    public PaymentOrder updatePaymentOrder(PaymentOrderDto order) {
        Optional<PaymentOrder> actualPaymentOrder = this.paymentOrderRepository.findById(order.getPaymentOrderId());
        Optional<Order> actualOrder = this.orderRepository.findById(order.getOrderId());
        if (actualPaymentOrder.isPresent() && actualOrder.isPresent()) {
            PaymentOrder item = actualPaymentOrder.get();
            item.setStatus(order.getStatus());
            Order newOrder = actualOrder.get();
            item.setOrder(newOrder);
            return this.paymentOrderRepository.save(item);
        }
        return null;
    }
}
