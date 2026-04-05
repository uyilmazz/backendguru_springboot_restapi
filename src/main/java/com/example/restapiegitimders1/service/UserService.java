package com.example.restapiegitimders1.service;

import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.restapiegitimders1.entity.User;
import com.example.restapiegitimders1.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public User loadByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new EntityNotFoundException("User not found with username : " + username));
	}
	
	public User register(String username, String password, Set<String> roles) {
		if(userRepository.existsByUsername(username))
			throw new IllegalArgumentException("Username already exists : " + username);
		
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(passwordEncoder.encode(password));
		newUser.setRoles(roles);
		
		return userRepository.save(newUser);
	}
}
