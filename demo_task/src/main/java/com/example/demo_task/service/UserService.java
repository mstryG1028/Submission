package com.example.demo_task.service;

import org.springframework.stereotype.Service;

import com.example.demo_task.exception.ResourseNotFoundException;
import com.example.demo_task.model.User;
import com.example.demo_task.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User login(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())
				.orElseThrow(() -> new ResourseNotFoundException("Invalid username or password"));
	}
}