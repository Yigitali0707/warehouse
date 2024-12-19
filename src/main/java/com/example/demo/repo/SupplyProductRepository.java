package com.example.demo.repo;

import com.example.demo.entity.SupplyProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplyProductRepository extends JpaRepository<SupplyProduct, UUID> {
}