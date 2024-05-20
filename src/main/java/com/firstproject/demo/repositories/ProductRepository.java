package com.firstproject.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstproject.demo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
