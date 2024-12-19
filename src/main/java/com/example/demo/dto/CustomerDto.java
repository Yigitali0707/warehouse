package com.example.demo.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.example.demo.entity.Customer}
 */
public record CustomerDto(String firstName, String lastName, Integer age, String phoneNumber) implements Serializable {
}