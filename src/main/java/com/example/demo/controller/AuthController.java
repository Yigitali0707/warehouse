package com.example.demo.controller;


import com.example.demo.dto.LoginDto;
import com.example.demo.dto.TokenResponse;
import com.example.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        try {
            var auth = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            authenticationManager.authenticate(auth);
            String accesToken = "Bearer "+ jwtUtil.generateToken(loginDto.getUsername());
            String refreshToken = "Bearer "+ jwtUtil.generateRefreshToken(loginDto.getUsername());
            return ResponseEntity.ok(
                    new TokenResponse(accesToken, refreshToken)
            );
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(403).body("bad attampt");
        }
    }


}
