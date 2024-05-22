package com.firstproject.demo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.firstproject.demo.entities.Category;
import com.firstproject.demo.entities.Order;
import com.firstproject.demo.entities.OrderProduct;
import com.firstproject.demo.entities.Payment;
import com.firstproject.demo.entities.Product;
import com.firstproject.demo.entities.User;
import com.firstproject.demo.entities.enums.OrderStatus;
import com.firstproject.demo.repositories.CategoryRepository;
import com.firstproject.demo.repositories.OrderProductRepository;
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
	
	@Autowired
	private OrderProductRepository orderProductRepository;
	
	// Create tables
	@Override
	public void run(String... args) throws Exception {
		Category category1 = new Category(null, "Eletronics");
		Category category2 = new Category(null, "Laptops");
		Category category3 = new Category(null, "Books");
		Category category4 = new Category(null, "Computer");
		
		Product product1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product product2 = new Product(null, "Smart TV 4K", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product product3 = new Product(null, "Macbook Air", "Nam eleifend maximus tortor, at mollis.", 5600.0, "");
		Product product4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product product5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		
		categoryRepository.saveAll(Arrays.asList(category1, category2, category3, category4));
		
		product1.getCategories().add(category3);
		product2.getCategories().add(category1);
		product3.getCategories().add(category2);
		product3.getCategories().add(category4);
		product4.getCategories().add(category4);
		product5.getCategories().add(category3);
		
		productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));
		
		User user1 = new User(null, "Alexandre", "alexandre@test.com", "122222222", "123456");
		User user2 = new User(null, "Maria", "maria@test.com", "133333333", "654321");	
		Order order1 = new Order(null, Instant.parse("2024-07-20T15:50:03Z"), OrderStatus.PAID, user1);
		Order order2 = new Order(null, Instant.parse("2024-05-17T13:21:22Z"), OrderStatus.WAITING_PAYMENT, user1);
		Order order3 = new Order(null, Instant.parse("2024-08-25T20:33:07Z"), OrderStatus.SHIPPED, user2);
		Order order4 = new Order(null, Instant.parse("2024-09-10T05:43:01Z"), OrderStatus.DELIVERED, user2);
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		orderRepository.saveAll(Arrays.asList(order1, order2, order3, order4));
		
		OrderProduct oi1 = new OrderProduct(order1, product1, 2, product1.getPrice());
		OrderProduct oi2 = new OrderProduct(order1, product2, 1, product3.getPrice());	
		OrderProduct oi3 = new OrderProduct(order3, product3, 2, product3.getPrice());
		OrderProduct oi4 = new OrderProduct(order4, product5, 2, product4.getPrice()); 
		
		orderProductRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment payment1 = new Payment(null, Instant.parse("2024-07-20T19:50:03Z"), order1);
		order1.setPayment(payment1);
		
		orderRepository.save(order1);
	}
}
