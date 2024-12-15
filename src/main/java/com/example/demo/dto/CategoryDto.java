package com.example.demo.dto;

import com.example.demo.entity.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Category}
 */
@Value
public class CategoryDto implements Serializable {
    @NotNull
    @NotEmpty
    String name;
    @NotNull
    @NotEmpty
    String description;

}