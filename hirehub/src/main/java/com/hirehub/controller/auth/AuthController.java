package com.hirehub.controller.auth;

import com.hirehub.dto.AuthResponse;
import com.hirehub.dto.LoginRequest;
import com.hirehub.dto.RegisterRequest;
import com.hirehub.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        return new AuthResponse(token);
    }

    @GetMapping("/test")
    public String test() {
        return "Protected route working!";
    }

    @GetMapping("/admin")
    public String adminOnly() {
        return "Only admin can see this";
    }


}