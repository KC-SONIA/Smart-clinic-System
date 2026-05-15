package com.smartclinic.backend.service.impl;

import com.smartclinic.backend.dto.RegisterRequest;
import com.smartclinic.backend.dto.LoginRequest;
import com.smartclinic.backend.entity.User;
import com.smartclinic.backend.repository.UserRepository;
import com.smartclinic.backend.security.JwtService;
import com.smartclinic.backend.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String register(RegisterRequest request) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);

        return "User Registered Successfully";
    }
    @Override
    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        boolean isPasswordMatch =
                passwordEncoder.matches(
                    request.getPassword(),
                    user.getPassword()
                );

        if (!isPasswordMatch) {
            throw new RuntimeException("Invalid Password");
        }

        return jwtService.generateToken(user.getEmail());
    }
}