package com.example.demo.service;

import com.example.demo.controller.OrderController;
import com.example.demo.dto.OrderDto;
import com.example.demo.dto.OrderProductDto;
import com.example.demo.dto.SupplyDto;
import com.example.demo.dto.SupplyProductDto;
import com.example.demo.entity.*;
import com.example.demo.repo.CustomerRepository;
import com.example.demo.repo.OrderProductRepository;
import com.example.demo.repo.OrderRepository;
import com.example.demo.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public Page<Order> getAllOrders(Pageable pageable) {
        Page<Order> orders=orderRepository.findAll(pageable);
    return orders;
    }

    @Transactional
    public String addOrder(OrderDto orderDto) {

       Order order=new Order();
        Customer customer = customerRepository.findById(orderDto.customerId()).orElseThrow(() -> new RuntimeException("Customer not found"));
        order.setCustomer(customer);
        orderRepository.save(order);

        List<OrderProductDto> orderProductDtos=orderDto.orderProducts();
        List<OrderProduct> orderProducts=new ArrayList<>();

        for (OrderProductDto orderProductDto : orderProductDtos) {

            Product product = productRepository.findById(orderProductDto.productId())
                    .orElseThrow(() -> new RuntimeException("Product Not Found!"));
            Integer count = orderProductDto.count();
            product.setReservedQuantity(product.getReservedQuantity() + count);
            product.setQuantity(product.getQuantity()-count);
            productRepository.save(product);


            OrderProduct orderProduct = OrderProduct.builder()
                    .product(product)
                    .order(order)
                    .count(count)
                    .build();
            orderProducts.add(orderProduct);
        }


        orderProductRepository.saveAll(orderProducts);
        order.setOrderProducts(orderProducts);
        orderRepository.save(order);

        return "Order Saved Successful!";
    }

}