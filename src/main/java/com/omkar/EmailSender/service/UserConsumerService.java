package com.omkar.EmailSender.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.omkar.EmailSender.entity.User;

@Service
public class UserConsumerService {
	@Autowired
    private JavaMailSender mailSender;

    @KafkaListener(topics = "userMailsTopic", groupId = "user-group",containerFactory = "listenerContainerFactory")
    public void consumeUserFromKafka(User user) {
    	SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Your Daily Email");
        message.setText("This is the email content.");
        mailSender.send(message);
    }
}