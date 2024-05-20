package com.firstproject.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstproject.demo.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
