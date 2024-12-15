package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public HttpEntity<?> getAllCategory(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(categoryService.getAllCategory(pageable));
    }

    @PostMapping("/add")
    public HttpEntity<?> addCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.addCategory(categoryDto));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateCategory(@PathVariable UUID id,@RequestParam(required = false)String name,@RequestParam(required = false)String description ) {
        return ResponseEntity.ok(categoryService.updateCategory(id, name,description));
    }
}
