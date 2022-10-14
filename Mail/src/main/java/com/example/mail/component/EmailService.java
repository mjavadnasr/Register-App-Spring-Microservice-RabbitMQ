package com.example.mail.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;



    @RabbitListener(queues = "Email")
    public void getEmail(byte[] message) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(message);
        ObjectInput objectInput = new ObjectInputStream(bis);

        String email = (String) objectInput.readObject();
        bis.close();
        objectInput.close();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply@baeldung.com");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Welcome message");
        simpleMailMessage.setText("Welcome to Our Application");
        emailSender.send(simpleMailMessage);
    }

}
