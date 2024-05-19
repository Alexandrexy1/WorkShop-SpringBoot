package com.firstproject.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.firstproject.demo.entities.User;
import com.firstproject.demo.repositories.UserRepository;

// takes the "test profile" from application.properties and the "test config" from application-test.properties
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	// inject dependency JpaRepository (CRUD simply)
	@Autowired
	private UserRepository userRepository;
	
	// Create users in table tb_user
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User(null, "Alexandre", "alexandre@test.com", "122222222", "123456");
		User user2 = new User(null, "Maria", "maria@test.com", "133333333", "654321");	
		
		userRepository.saveAll(Arrays.asList(user1, user2));
	}
}
