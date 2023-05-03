package com.enigma.service;

import com.enigma.model.request.LoginRequest;
import com.enigma.model.request.RegistrationRequest;

public interface AuthService {
    String register(RegistrationRequest registrationRequest);
    String login(LoginRequest loginRequest);
}
