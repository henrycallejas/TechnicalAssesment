package com.test.Technical.Assesment.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Technical.Assesment.dto.OrderDetailDto;
import com.test.Technical.Assesment.dto.ProductDto;
import com.test.Technical.Assesment.model.Client;
import com.test.Technical.Assesment.model.Order;
import com.test.Technical.Assesment.model.OrderDetail;
import com.test.Technical.Assesment.model.Product;
import com.test.Technical.Assesment.repository.ClientRepository;
import com.test.Technical.Assesment.repository.OrderDetailRepository;
import com.test.Technical.Assesment.repository.OrderRepository;
import com.test.Technical.Assesment.repository.ProductRepository;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductServiceImp productServiceImp;

    @Override
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> order = this.orderRepository.findById(id);
        return order.orElse(null);
    }

    @Override
    public OrderDetail createOrder(OrderDetailDto order) {
        Order newOrder = new Order();
        Client client = clientRepository.findById(order.getClientId())
        .orElseThrow(() -> new RuntimeException("Client not found"));
        newOrder.setClient(client);
        newOrder.setOrderDate(new Date());
        newOrder = this.orderRepository.save(newOrder);
        OrderDetail orderDetail = new OrderDetail();

        if(newOrder.getOrderId() != null){
            orderDetail.setOrder(newOrder);
            orderDetail.setAmount(order.getAmount());
            orderDetail.setUnitPrice(order.getUnitPrice());
            ProductDto productDto = productServiceImp.getProductById(order.getProductId());
            Product product = new Product();
            product.setDescription(productDto.getDescription());
            product.setImage(productDto.getImage());
            product.setName(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            Optional<Product> founProduct = this.productRepository.findById(productDto.getId());
            if(!founProduct.isPresent()){
                this.productRepository.save(product);
            }else{
                product.setProductId(founProduct.get().getProductId());
            }
            orderDetail.setProduct(product);
            orderDetailRepository.save(orderDetail);
        }
        return orderDetail;
    }
}
