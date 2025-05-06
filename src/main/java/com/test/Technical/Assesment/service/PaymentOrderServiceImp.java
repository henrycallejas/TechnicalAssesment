package com.test.Technical.Assesment.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Technical.Assesment.dto.PaymentOrderDto;
import com.test.Technical.Assesment.enums.PaymentStatus;
import com.test.Technical.Assesment.model.Client;
import com.test.Technical.Assesment.model.Order;
import com.test.Technical.Assesment.model.OrderDetail;
import com.test.Technical.Assesment.model.PaymentOrder;
import com.test.Technical.Assesment.model.Product;
import com.test.Technical.Assesment.repository.ClientRepository;
import com.test.Technical.Assesment.repository.OrderRepository;
import com.test.Technical.Assesment.repository.PaymentOrderRepository;
import com.test.Technical.Assesment.repository.ProductRepository;
import com.test.Technical.Assesment.utils.CardValidator;

@Service
public class PaymentOrderServiceImp implements PaymentOrderService {

    @Autowired
    PaymentOrderRepository paymentOrderRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProductRepository productRepository;

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
    public Map<String, Object> processPayment(Long clientId, PaymentOrderDto detailsDto) {
        Map<String, Object> response = new HashMap<>();
        String expiringDate = CardValidator.getExpiringDate(detailsDto);

        if (CardValidator.isCardNumberValid(detailsDto.getCardNumber())
                && CardValidator.isExpiringDateValid(expiringDate)) {
            Client client = this.clientRepository.findById(clientId)
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            Order newOrder = new Order();
            newOrder.setClient(client);
            newOrder.setOrderDate(new Date());

            List<OrderDetail> details = detailsDto.getDetails().stream().map(dto -> {
                OrderDetail detail = new OrderDetail();
                detail.setAmount(dto.getAmount());
                detail.setUnitPrice(dto.getUnitPrice());
                detail.setOrder(newOrder);
                Product product = this.productRepository.findById(dto.getProductId()).get();
                detail.setProduct(product);
                return detail;
            }).collect(Collectors.toList());
            newOrder.setOrderDetails(details);
            Order orderCreated = this.orderRepository.save(newOrder);

            PaymentOrder paymentOrder = new PaymentOrder();
            paymentOrder.setPaymentDate(new Date());
            paymentOrder.setStatus(PaymentStatus.APROBADO);
            BigDecimal totalAmount = details.stream()
                    .map(d -> d.getUnitPrice().multiply(BigDecimal.valueOf(d.getAmount())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            paymentOrder.setTotalAmount(totalAmount);
            paymentOrder.setOrder(orderCreated);
            this.paymentOrderRepository.save(paymentOrder);

            List<PaymentOrder> paymentOrders = this.paymentOrderRepository.findByOrder(orderCreated);
            orderCreated.setPaymentOrders(paymentOrders);
            response.put("response", orderCreated);
            return response;
        }
        if (!CardValidator.isCardNumberValid(detailsDto.getCardNumber())) {
            response.put("response", "Número de tarjeta no válido.");
            return response;
        } else if (!CardValidator.isExpiringDateValid(expiringDate)) {
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
