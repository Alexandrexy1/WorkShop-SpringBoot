package com.firstproject.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstproject.demo.entities.Order;
import com.firstproject.demo.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> findAll() {
		return orderRepository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> object = orderRepository.findById(id);
		
		return object.get();
	}
}
