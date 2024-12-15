package com.example.demo.dto;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.example.demo.entity.User}
 */
public record UserDTO(String firstName, String lastName,Integer age, String username,  String password) implements Serializable {
}