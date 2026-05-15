package com.smartclinic.backend.service;

import com.smartclinic.backend.dto.RegisterRequest;
import com.smartclinic.backend.dto.LoginRequest;

public interface AuthService {
    
    String register(RegisterRequest request);
    String login(LoginRequest request);
}