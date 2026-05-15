package com.smartclinic.backend.service;

import com.smartclinic.backend.dto.RegisterRequest;

public interface AuthService {
    
    String register(RegisterRequest request);
}