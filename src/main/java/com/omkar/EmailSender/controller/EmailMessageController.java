package com.omkar.EmailSender.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omkar.EmailSender.entity.User;
import com.omkar.EmailSender.repository.UserRepository;
import com.omkar.EmailSender.service.EmailMessageService;

import jakarta.websocket.server.PathParam;

@RequiredArgsConstructor
@RestController
public class EmailMessageController {
	@Autowired
    private EmailMessageService emailMessageService;
	 @Autowired
	    private JavaMailSender mailSender;

	    @Scheduled(cron = "0 * * * * *") // This cron expression represents 1 PM every day "0 0 13 * * ?"
	    public void sendEmails() {
	        emailMessageService.readUserDataInBatches();
	    }
	
	    @RequestMapping(value = "/sendmail/{email}")
	    public void sendEmail(@PathVariable String email) {
//	           SimpleMailMessage message = new SimpleMailMessage();
//	            message.setTo(email);
//	            message.setSubject("Your Daily Email");
//	            message.setText("This is the email content.");
//	            mailSender.send(message);
	    	emailMessageService.readUserDataInBatches();
	    }
}
