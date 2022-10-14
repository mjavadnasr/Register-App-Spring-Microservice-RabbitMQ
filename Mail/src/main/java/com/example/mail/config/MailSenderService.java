package com.example.mail.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("m.javad.nasrolla@gmail.com");
        message.setTo(to);
        message.setSubject("Welcome ");
        message.setText("Welcome to Our Application");

        mailSender.send(message);
    }

}
