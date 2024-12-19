package com.example.demo.dto;

import com.example.demo.entity.SupplyProduct;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link SupplyProduct}
 */
public record SupplyProductDto(UUID productId, Integer count) implements Serializable {
  }