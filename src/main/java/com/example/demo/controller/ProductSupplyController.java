package com.example.demo.controller;

import com.example.demo.dto.SupplyDto;
import com.example.demo.service.ProductSupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/supply")
public class ProductSupplyController {
    private final ProductSupplyService productSupplyService;

    @PostMapping
    public HttpEntity<?> productSupply(@RequestBody SupplyDto supplyDto) {
        return ResponseEntity.ok(productSupplyService.supply(supplyDto));
    }
}
