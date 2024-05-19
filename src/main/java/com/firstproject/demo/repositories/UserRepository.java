package com.firstproject.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstproject.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
