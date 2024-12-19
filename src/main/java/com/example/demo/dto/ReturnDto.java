package com.example.demo.dto;

import com.example.demo.entity.Return;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link Return}
 */
public record ReturnDto(List<ReturnProductDto> returnProducts, UUID customerId) implements Serializable {
}