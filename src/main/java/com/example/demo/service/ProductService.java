package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

import com.example.demo.mapper.ProductMapper;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;


    @Transactional
    public Product addProduct(UUID categoryId, String name, String barcode, MultipartFile photo) throws IOException {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category Not Found"));
        Product product=new Product();
        product.setCategory(category);
        product.setName(name);
        product.setQuantity(0L);
        product.setReservedQuantity(0L);
        if(barcode!=null){
            product.setBarcode(barcode);
        }
        if(photo!=null&& !photo.isEmpty()){
            product.setPhoto(photo.getBytes());
        }
        return productRepository.save(product);
    }


    public Page<ProductDto> getAllProducts(Pageable pageable) {
        Page<Product> products=productRepository.findAll(pageable);
        Page <ProductDto> productDtos=products.map(productMapper::toDto);
        return productDtos;
    }

    public List<ProductDto> categoryProducts(UUID categoryId) {
        List<Product> products = productRepository.findAllByCategoryId(categoryId);
        List<ProductDto> productDtos=new ArrayList<>();
        for (Product product : products) {
           productDtos.add( productMapper.toDto(product));
        }
         return productDtos;
    }





    public ProductDto updateProduct(UUID id, String name, String barcode, UUID categoryId, MultipartFile photo) throws IOException {
        Product product=productRepository.findById(id).orElseThrow(()->new RuntimeException("Product Not Found!"));
        if(name!=null){
            logger.warn("Product name edited: {}->{}",product.getName(),name);
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
        return productMapper.toDto(product);
    }

    public List<ProductDto> find(String name) {
        List<Product> products= productRepository.findByName(name);
        List<ProductDto> productDtos=new ArrayList<>();
        for (int i = 0; i <products.size(); i++) {
            productDtos.add(productMapper.toDto(products.get(i)));
        }
        return productDtos;
    }


}
