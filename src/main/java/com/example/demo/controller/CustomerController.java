package com.example.demo.controller;

import com.example.demo.dto.CustomerDto;
import com.example.demo.repo.CustomerRepository;
import com.example.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerService customerService;

    @GetMapping
    public HttpEntity<?> getAllCustomer(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size){
        Pageable pageable= PageRequest.of(page,size);
        return ResponseEntity.ok(customerService.getAllCustomer(pageable));
    }

    @PostMapping("/add")
    public HttpEntity<?> addCustomer(@RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(customerService.addCustomer(customerDto));
    }

    @PutMapping("update")
    public HttpEntity<?> updateCustomer(UUID id,@RequestParam(required = false) String firstName,
                                        @RequestParam(required = false) String lastName,
                                        @RequestParam(required = false) Integer age,
                                        @RequestParam(required = false) String phoneNumber){
        return ResponseEntity.ok(customerService.updateCustomer(id,firstName,lastName,age,phoneNumber));
    }


    @GetMapping("/find")
    public HttpEntity<?> findCustomer(String find){
        return ResponseEntity.ok(customerService.findCustomer(find));
    }
}
