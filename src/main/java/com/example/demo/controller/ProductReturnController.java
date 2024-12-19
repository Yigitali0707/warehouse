package com.example.demo.controller;

import com.example.demo.dto.ReturnDto;
import com.example.demo.service.ProductReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productReturn")
@RequiredArgsConstructor
public class ProductReturnController {

    private final ProductReturnService productReturnService;

    @PostMapping
    public HttpEntity<?> productReturn(@RequestBody ReturnDto returnDto){
        return ResponseEntity.ok(productReturnService.productReturn(returnDto));
    }
}
