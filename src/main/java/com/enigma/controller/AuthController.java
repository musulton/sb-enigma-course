package com.enigma.controller;

import com.enigma.model.request.LoginRequest;
import com.enigma.model.request.RegistrationRequest;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.AuthService;
import com.enigma.util.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMapping.AUTH)
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(UrlMapping.REGISTER)
    public ResponseEntity register(@RequestBody RegistrationRequest registrationRequest) {
        String token = authService.register(registrationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success registration", token));
    }

    @PostMapping(UrlMapping.LOGIN)
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success login", token));
    }
}
