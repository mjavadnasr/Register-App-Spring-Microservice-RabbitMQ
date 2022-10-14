package com.example.notification.controller;

import com.example.notification.model.UserModel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping("/send-welcome/{email}")
    public void getUser(@PathVariable("email") String  email) throws IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput objectOutput = new ObjectOutputStream(bos);
        objectOutput.writeObject(email);
        objectOutput.flush();
        objectOutput.close();

        byte[] bytes = bos.toByteArray();
        bos.close();

        Message message = MessageBuilder.withBody(bytes).build();

        rabbitTemplate.send("Direct-Exchange" , "email" , message);
    }
}