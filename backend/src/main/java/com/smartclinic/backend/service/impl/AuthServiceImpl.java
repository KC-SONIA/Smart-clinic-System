package com.smartclinic.backend.service.impl;

import com.smartclinic.backend.dto.RegisterRequest;
import com.smartclinic.backend.entity.User;
import com.smartclinic.backend.repository.UserRepository;
import com.smartclinic.backend.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
}