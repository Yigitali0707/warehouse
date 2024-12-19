package com.example.demo.repo;

import com.example.demo.entity.OrderProduct;
import com.example.demo.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
    @Query("SELECT op FROM OrderProduct op " +
            "JOIN op.order o " +
            "WHERE o.customer.id = :customerId AND op.status = :status")
    List<OrderProduct> findAllByCustomerIdAndStatus(@Param("customerId") UUID customerId, @Param("status") Status status);
}