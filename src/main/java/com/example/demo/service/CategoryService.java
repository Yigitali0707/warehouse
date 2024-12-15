package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public HttpEntity<?> getAllCategory(Pageable pageable) {

        Page<Category> categories = categoryRepository.findAll(pageable);

        return ResponseEntity.ok(categories);
    }


    public HttpEntity<?> addCategory(CategoryDto categoryDto) {
       Category category= categoryMapper.toEntity(categoryDto);
       return ResponseEntity.ok(categoryRepository.save(category));
    }

    public HttpEntity<?> updateCategory(UUID id,String name,String description) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if(name!=null){
            category.setName(name);
        }
        if(description!=null){
            category.setDescription(description);
        }

       categoryRepository.save(category);

        return ResponseEntity.ok(category);
    }


}
