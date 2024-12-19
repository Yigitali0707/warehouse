package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.repo.OrderRepository;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

   private final OrderService orderService;

    @GetMapping
    public HttpEntity<?> getAllOrders(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size){
        Pageable pageable=PageRequest.of(page,size);
        return ResponseEntity.ok(orderService.getAllOrders(pageable));
    }


    @PostMapping
    public HttpEntity<?> addOrder(@RequestBody OrderDto orderDto){

        return ResponseEntity.ok(orderService.addOrder(orderDto));
    }


    @GetMapping("/getCustomerOrders")
    public HttpEntity<?> getCustomerOrders(UUID customerId){
        return ResponseEntity.ok(orderService.getCustomerOrderProducts(customerId));
    }

}
