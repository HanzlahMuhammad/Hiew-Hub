package com.hirehub.service.auth;

import com.hirehub.dto.LoginRequest;
import com.hirehub.dto.RegisterRequest;
import com.hirehub.entity.User;
import com.hirehub.exception.CustomException;
import com.hirehub.repository.UserRepository;
import com.hirehub.security.JwtService; // ✅ import added
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("CANDIDATE")
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException("Invalid password");
        }

        return jwtService.generateToken(user.getEmail());
    }
}