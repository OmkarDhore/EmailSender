package com.omkar.EmailSender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.omkar.EmailSender.entity.User;

@Service
public class UserProducerService {

	@Autowired
    private KafkaTemplate<String, User> kafkaTemplate;


    public void sendUserToKafka(User user) {
        this.kafkaTemplate.send("userMailsTopic", user);
    }
}