package com.example.demo.service;

import com.example.demo.dto.SupplyDto;
import com.example.demo.dto.SupplyProductDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.Supply;
import com.example.demo.entity.SupplyProduct;
import com.example.demo.repo.ProductRepository;
import com.example.demo.repo.SupplyProductRepository;
import com.example.demo.repo.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSupplyService {
    private final SupplyRepository supplyRepository;
    private final SupplyProductRepository supplyProductRepository;
    private final ProductRepository productRepository;


    @Transactional
    public String supply(SupplyDto supplyDto) {

        Supply supply = new Supply();
        supplyRepository.save(supply);

        List<SupplyProductDto> supplyProductDtos = supplyDto.supplyProducts();
        List<SupplyProduct> supplyProducts = new ArrayList<>();

        for (SupplyProductDto supplyProductDto : supplyProductDtos) {

            Product product = productRepository.findById(supplyProductDto.productId())
                    .orElseThrow(() -> new RuntimeException("Product Not Found!"));
            Integer count = supplyProductDto.count();
            product.setQuantity(product.getQuantity() + count);
            productRepository.save(product);


            SupplyProduct supplyProduct = SupplyProduct.builder()
                    .product(product)
                    .supply(supply)
                    .count(count)
                    .build();
            supplyProducts.add(supplyProduct);
        }


        supplyProductRepository.saveAll(supplyProducts);
        supply.setSupplyProducts(supplyProducts);
        supplyRepository.save(supply);

        return "Product Supplied";
    }

}
