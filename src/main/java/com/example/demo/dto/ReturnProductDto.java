package com.example.demo.dto;

import com.example.demo.entity.ReturnProduct;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link ReturnProduct}
 */
public record ReturnProductDto(UUID productId, Integer count) implements Serializable {
}