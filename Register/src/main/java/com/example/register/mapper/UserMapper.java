package com.example.register.mapper;

import com.example.register.entity.User;
import com.example.register.model.UserModel;

public class UserMapper {

    public User toEntity(UserModel userModel) {
        User user = new User();
        user.setFirstname(userModel.getFirstname());
        user.setLastname(userModel.getLastname());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
        user.setPhoneNumber(userModel.getPhoneNumber());
        return user;
    }
}