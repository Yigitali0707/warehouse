package com.example.demo.dto;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.example.demo.entity.OrderProduct}
 */
public record OrderProductDto(UUID productId, Integer count) implements Serializable {
}