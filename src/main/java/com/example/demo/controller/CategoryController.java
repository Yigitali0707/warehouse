package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public HttpEntity<?>getAllCategory(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size){
        Pageable pageable=Pageable.ofSize(size).withPage(page);
      return categoryService.getAllCategory(pageable);
    }

    @PostMapping("/add")
    public HttpEntity<?> addCategory(@RequestBody Category category){
          return categoryService.addCategory(category);
    }

    @PatchMapping("/{id}")
    public HttpEntity<?> updateCategory(
            @PathVariable UUID id,
            @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(categoryService.updateCategory(id, updates));
    }


}
