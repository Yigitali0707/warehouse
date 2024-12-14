package com.example.demo.service;

import com.example.demo.controller.CategoryController;
import com.example.demo.entity.Category;
import com.example.demo.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public HttpEntity<?> getAllCategory(Pageable pageable) {
        return ResponseEntity.ok(categoryRepository.findAll(pageable));
    }

    public HttpEntity<?> addCategory(Category category) {
       return ResponseEntity.ok(categoryRepository.save(category));
    }

    public HttpEntity<?> updateCategory(UUID id, Map<String, Object> updates) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> {
                    if (value instanceof String && !((String) value).isBlank()) {
                        category.setName((String) value);
                    } else {
                        throw new IllegalArgumentException("Invalid value for name");
                    }
                }
                case "description" -> {
                    if (value instanceof String && !((String) value).isBlank()) {
                        category.setDescription((String) value);
                    } else {
                        throw new IllegalArgumentException("Invalid value for description");
                    }
                }
                default -> throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return ResponseEntity.ok(categoryRepository.save(category));
    }


}
