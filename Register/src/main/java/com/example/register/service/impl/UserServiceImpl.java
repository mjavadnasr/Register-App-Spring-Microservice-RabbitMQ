package com.example.register.service.impl;

import com.example.register.DAO.UserDAO;
import com.example.register.entity.User;
import com.example.register.mapper.UserMapper;
import com.example.register.model.UserModel;
import com.example.register.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void saveUser(UserModel userModel) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.toEntity(userModel);
//        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userDAO.save(user);

        String url = "http://localhost:8085/notification/send-welcome/{userModel}";
        Map<String, UserModel> urlParams = new HashMap<>();
        urlParams.put("userModel", userModel);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        restTemplate.getForObject(builder.buildAndExpand(urlParams).toUri(), UserModel.class);

    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }
}
