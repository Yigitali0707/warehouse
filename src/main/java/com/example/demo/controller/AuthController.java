package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.service.UserService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.username(),loginRequest.password());
    }



}
