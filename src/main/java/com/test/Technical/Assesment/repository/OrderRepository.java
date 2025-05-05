package com.test.Technical.Assesment.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.Technical.Assesment.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
Optional<Order> findByOrderId(Long orderId);
}
