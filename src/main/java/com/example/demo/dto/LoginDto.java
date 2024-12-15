package com.example.demo.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class LoginDto implements Serializable {
    String username;
    String password;
}