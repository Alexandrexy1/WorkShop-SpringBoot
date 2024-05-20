package com.firstproject.demo.config;

import java.time.Instant;
import java.util.Arrays;
import java.lang.Exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.firstproject.demo.entities.Order;
import com.firstproject.demo.entities.User;
import com.firstproject.demo.entities.enums.OrderStatus;
import com.firstproject.demo.repositories.OrderRepository;
import com.firstproject.demo.repositories.UserRepository;


// takes the "test profile" from application.properties and the "test config" from application-test.properties
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired // inject dependency JpaRepository (CRUD simply)
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	// Create users in table tb_user
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User(null, "Alexandre", "alexandre@test.com", "122222222", "123456");
		User user2 = new User(null, "Maria", "maria@test.com", "133333333", "654321");	
		Order order1 = new Order(null, Instant.parse("2024-07-20T15:50:03Z"), OrderStatus.PAID, user1);
		Order order2 = new Order(null, Instant.parse("2024-05-17T13:21:22Z"), OrderStatus.WAITING_PAYMENT, user1);
		Order order3 = new Order(null, Instant.parse("2024-08-25T20:33:07Z"), OrderStatus.SHIPPED, user2);
		Order order4 = new Order(null, Instant.parse("2024-09-10T05:43:01Z"), OrderStatus.DELIVERED, user2);
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		orderRepository.saveAll(Arrays.asList(order1, order2, order3, order4));
	}
}
