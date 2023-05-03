package com.enigma.service;

import com.enigma.exception.EntityExistException;
import com.enigma.exception.NotFoundException;
import com.enigma.exception.UnauthorizedException;
import com.enigma.model.Auth;
import com.enigma.model.User;
import com.enigma.model.request.LoginRequest;
import com.enigma.model.request.RegistrationRequest;
import com.enigma.repository.AuthRepository;
import com.enigma.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    AuthRepository authRepository;
    UserService userService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    public AuthServiceImpl(AuthRepository authRepository, UserService userService) {
        this.authRepository = authRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public String register(RegistrationRequest registrationRequest) {
        try {
            Auth auth = modelMapper.map(registrationRequest, Auth.class);
            Auth authResult = authRepository.save(auth);

            User user = modelMapper.map(registrationRequest, User.class);
            user.setAuth(authResult);
            userService.updateById(user);
            String token = jwtUtil.generateToken(user.getAuth().getEmail());
            return token;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Transactional
    @Override
    public String login(LoginRequest loginRequest) {
        try {
            Optional<Auth> auth = authRepository.findById(loginRequest.getEmail());
            if (auth.isEmpty()) throw new NotFoundException();
            if (!auth.get().getPassword().equals(loginRequest.getPassword())) {
                throw new UnauthorizedException("Password not matched");
            }

            String token = jwtUtil.generateToken(loginRequest.getEmail());
            return token;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
