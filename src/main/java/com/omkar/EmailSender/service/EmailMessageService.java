package com.omkar.EmailSender.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
//import org.springframework.kafka.core.KafkaTemplate;
import com.omkar.EmailSender.entity.User;
import com.omkar.EmailSender.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class EmailMessageService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	 
	@Transactional
	public void readUserDataInBatches() {
		int pageNumber = 0;
		int pageSize = 100;

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> userPage;

		do {
			userPage = userRepository.findAll(pageable);

			List<User> users = userPage.getContent();
			users.forEach(user -> kafkaTemplate.send("userMailsTopic", user));
			
			pageable = userPage.nextPageable();
        } while (userPage.hasNext());
	}
}
