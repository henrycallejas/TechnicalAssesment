package com.test.Technical.Assesment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.Technical.Assesment.model.Order;
import com.test.Technical.Assesment.model.PaymentOrder;
import java.util.List;


@Repository
public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {
    List<PaymentOrder> findByOrder(Order order);
}
