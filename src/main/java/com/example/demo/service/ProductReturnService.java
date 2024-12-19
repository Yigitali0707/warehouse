package com.example.demo.service;

import com.example.demo.dto.ReturnDto;
import com.example.demo.dto.ReturnProductDto;
import com.example.demo.entity.*;
import com.example.demo.repo.CustomerRepository;
import com.example.demo.repo.ProductRepository;
import com.example.demo.repo.ReturnProductRepository;
import com.example.demo.repo.ReturnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductReturnService {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final ReturnRepository returnRepository;
    private final ReturnProductRepository returnProductRepository;


    public String productReturn(ReturnDto returnDto) {
        Return aReturn=new Return();
        Customer customer = customerRepository.findById(returnDto.customerId()).orElseThrow(() -> new RuntimeException("Customer not found"));
        aReturn.setCustomer(customer);
        returnRepository.save(aReturn);

        List<ReturnProductDto> returnProductDtos=returnDto.returnProducts();
        List<ReturnProduct> returnProducts=new ArrayList<>();

        for (ReturnProductDto returnProductDto : returnProductDtos) {

            Product product = productRepository.findById(returnProductDto.productId())
                    .orElseThrow(() -> new RuntimeException("Product Not Found!"));
            Integer count = returnProductDto.count();
            product.setReservedQuantity(product.getReservedQuantity() - count);
            product.setQuantity(product.getQuantity()+count);
            productRepository.save(product);



            ReturnProduct returnProduct = ReturnProduct.builder()
                    .product(product)
                    .aReturn(aReturn)
                    .count(count)
                    .build();
            returnProducts.add(returnProduct);
        }


        returnProductRepository.saveAll(returnProducts);
        aReturn.setReturnProducts(returnProducts);
        returnRepository.save(aReturn);

        return "Return Saved Successful!";
    }
}
