package com.example.register.service;

import com.example.register.entity.User;
import com.example.register.model.UserModel;

import java.util.List;

public interface UserService {
    void saveUser(UserModel userModel);
    List<User> findAll();
}
