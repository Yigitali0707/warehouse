package com.example.demo.dto;

import com.example.demo.entity.Product;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Product}
 */
@Value
public class ProductDto implements Serializable {
    UUID id;
    UUID categoryId;
    String name;
    String barcode;
    byte[] photo;
    Long quantity;
    Long reservedQuantity;
}