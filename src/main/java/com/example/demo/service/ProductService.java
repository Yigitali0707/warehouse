package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

import com.example.demo.mapper.ProductMapper;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;


    @Transactional
    public HttpEntity<?> addProduct(UUID categoryId, String name, String barcode, MultipartFile photo) throws IOException {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category Not Found"));
        Product product=new Product();
        product.setCategory(category);
        product.setName(name);
        if(barcode!=null){
            product.setBarcode(barcode);
        }
        if(photo!=null&& !photo.isEmpty()){
            product.setPhoto(photo.getBytes());
        }
        return ResponseEntity.ok(productRepository.save(product));
    }


    public HttpEntity<?> getAllProducts(Pageable pageable) {
        Page<Product> products=productRepository.findAll(pageable);
        Page <ProductDto> productDtos=products.map(productMapper::toDto);
        return ResponseEntity.ok(productDtos);
    }



    public HttpEntity<?> updateProduct(UUID id, String name, String barcode, UUID categoryId, MultipartFile photo) throws IOException {
        Product product=productRepository.findById(id).orElseThrow(()->new RuntimeException("Product Not Found!"));
        if(name!=null){
          product.setName(name);
        }
        if(barcode!=null){
            product.setBarcode(barcode);
        }
        if(categoryId!=null){
            Category category=categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category Not Found"));
            product.setCategory(category);
        }
        if(photo!=null && !photo.isEmpty()){
            product.setPhoto(photo.getBytes());
        }
        productRepository.save(product);
        return ResponseEntity.ok(productMapper.toDto(product));
    }
}
