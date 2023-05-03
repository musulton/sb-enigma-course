package com.enigma.controller;

import com.enigma.model.User;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.UserService;
import com.enigma.util.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(UrlMapping.USERS)
public class UserController {
    private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all user", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") String id) {
        Optional<User> user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get by id", user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete by id", null));
    }

    @PutMapping
    public ResponseEntity updateById(@RequestBody User user) {
        userService.updateById(user);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update by id", null));
    }
}
