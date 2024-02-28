package com.omkar.EmailSender.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkar.EmailSender.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{

}
