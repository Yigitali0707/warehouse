package com.example.demo.dto;

import com.example.demo.entity.enums.Status;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.example.demo.entity.OrderProduct}
 */
public record CustomerProductDto(UUID id, String productName, Integer count,
                                 @NotNull Status status) implements Serializable {
}