package com.firstproject.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstproject.demo.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
