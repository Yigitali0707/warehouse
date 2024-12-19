package com.example.demo.repo;

import com.example.demo.entity.ReturnProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReturnProductRepository extends JpaRepository<ReturnProduct, UUID> {
}