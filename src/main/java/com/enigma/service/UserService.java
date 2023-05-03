package com.enigma.service;

import com.enigma.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(String id);
    void deleteById(String id);
    void updateById(User user);

    User create(User user);
}
