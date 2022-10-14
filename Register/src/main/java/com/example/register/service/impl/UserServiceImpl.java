package com.example.register.service.impl;

import com.example.register.DAO.UserDAO;
import com.example.register.entity.User;
import com.example.register.mapper.UserMapper;
import com.example.register.model.UserModel;
import com.example.register.service.UserService;
import org.springframework.stereotype.Service;

@Service
public record UserServiceImpl(UserDAO userDAO )  implements UserService {
    @Override
    public void saveUser(UserModel userModel) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.toEntity(userModel);
//        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userDAO.save(user);

    }
}
