package com.example.jwt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;

    // Inject JwtService
    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginRequest) {

        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        // Basic credential verification
        if ("admin".equals(username) && "password123".equals(password)) {

            // Generate JWT token
            String token = jwtService.generateToken(username);

            return Map.of("token", token);

        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }
}