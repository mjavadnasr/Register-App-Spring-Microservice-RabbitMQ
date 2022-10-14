package com.example.mail.component;

import com.example.mail.config.MailSenderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
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
    private MailSenderService emailSender;

    @RabbitListener(queues = "Email")
    public void getEmail(byte[] message) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(message);
        ObjectInput objectInput = new ObjectInputStream(bis);

        String email = (String) objectInput.readObject();
        bis.close();
        objectInput.close();

        emailSender.sendEmail(email);
    }

}
