package com.example.register.controller;

import com.example.register.model.UserModel;
import com.example.register.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public record RegisterController(UserService userService) {

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserModel userModel)
    {
        userService.saveUser(userModel);
        return ResponseEntity.ok().body("User has been Registered successfully");
    }
}
