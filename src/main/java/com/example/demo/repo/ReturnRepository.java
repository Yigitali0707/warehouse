package com.example.demo.repo;

import com.example.demo.entity.Return;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReturnRepository extends JpaRepository<Return, UUID> {
}