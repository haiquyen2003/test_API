package com.example.eshoptestapi.controller;

import com.example.eshoptestapi.entity.User;
import com.example.eshoptestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setRole(User.Role.CUSTOMER);// đảm bảo răng khách hàng chỉ có thể đăng kí tk Customer
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
}
