package com.example.demo.repo;

import com.example.demo.entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplyRepository extends JpaRepository<Supply, UUID> {
}