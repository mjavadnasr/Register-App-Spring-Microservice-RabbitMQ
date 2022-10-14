package com.example.notification.controller;

import com.example.notification.model.UserModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @GetMapping("/send-welcome/{userModel}")
    public void getUser(@PathVariable("userModel") UserModel userModel)
    {
        String email = userModel.getEmail();
    }


}

