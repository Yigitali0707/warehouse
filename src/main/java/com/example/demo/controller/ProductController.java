package com.example.demo.controller;

import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public HttpEntity<?> addProduct(UUID categoryId,
                                    String name, @RequestParam(required = false) String barcode,
                                    @RequestParam(required = false) MultipartFile photo) throws IOException {
         return productService.addProduct(categoryId,name,barcode,photo);
    }

    @GetMapping
    public HttpEntity<?> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @PutMapping("/update")
    public HttpEntity<?> updateProduct(UUID id,@RequestParam(required = false) String name,
                                       @RequestParam(required = false) String barcode,
                                       @RequestParam(required = false) UUID categoryId,
                                       @RequestParam(required = false) MultipartFile photo) throws IOException {
        return ResponseEntity.ok(productService.updateProduct(id,name,barcode,categoryId,photo));
    }
}
