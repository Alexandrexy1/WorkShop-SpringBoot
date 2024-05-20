package com.firstproject.demo.config;

import java.time.Instant;
import java.util.Arrays;
import java.lang.Exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.firstproject.demo.entities.Category;
import com.firstproject.demo.entities.Order;
import com.firstproject.demo.entities.Product;
import com.firstproject.demo.entities.User;
import com.firstproject.demo.entities.enums.OrderStatus;
import com.firstproject.demo.repositories.CategoryRepository;
import com.firstproject.demo.repositories.OrderRepository;
import com.firstproject.demo.repositories.ProductRepository;
import com.firstproject.demo.repositories.UserRepository;


// takes the "test profile" from application.properties and the "test config" from application-test.properties
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired // inject dependency JpaRepository (CRUD simply)
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	// Create tables
	@Override
	public void run(String... args) throws Exception {
		
		
		Category category1 = new Category(null, "Eletronics");
		Category category2 = new Category(null, "Laptops");
		Category category3 = new Category(null, "Books");
		
		Product product1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product product2 = new Product(null, "Smart TV 4K", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product product3 = new Product(null, "Macbook Air", "Nam eleifend maximus tortor, at mollis.", 5600.0, "");
		Product product4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product product5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));
		categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
		
		
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
