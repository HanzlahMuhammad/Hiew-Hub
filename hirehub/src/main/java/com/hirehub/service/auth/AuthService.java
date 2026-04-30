package com.hirehub.service.auth;

import com.hirehub.dto.RegisterRequest;
import com.hirehub.entity.User;
import com.hirehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public String register(RegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role("CANDIDATE")
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }
}