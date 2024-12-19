package com.example.demo.dto;

import com.example.demo.entity.Supply;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Supply}
 */
public record SupplyDto(List<SupplyProductDto> supplyProducts) implements Serializable {
}