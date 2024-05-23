package com.firstproject.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.firstproject.demo.entities.User;
import com.firstproject.demo.repositories.UserRepository;
import com.firstproject.demo.services.exceptions.ResourceNotFoundException;
import com.firstproject.demo.services.exceptions.SpringDataBaseException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> object = userRepository.findById(id);
		
		return object.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User user) {
		return userRepository.save(user);
	}

	public void delete(Long id) {
		try {
			if(userRepository.existsById(id)) {
				userRepository.deleteById(id);				
			} else throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new SpringDataBaseException(e.getMessage());
		}
	}

	public User update(Long id, User obj) {
		try {
			User entity = userRepository.getReferenceById(id);
			updateUser(entity, obj);
			return userRepository.save(entity);
				
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateUser(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
