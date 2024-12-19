package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.example.demo.entity.Order}
 */
public record OrderDto(List<OrderProductDto> orderProducts, UUID customerId) implements Serializable {
}