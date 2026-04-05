package com.example.restapiegitimders1.service;

import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.restapiegitimders1.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	
	public long register(String username, String password, Set<String> roles) {
		User newUser = userService.register(username, password, roles);
		return newUser.getId();
	}
	
	public String login(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		User user = userService.loadByUsername(username);
		String token = jwtService.generateToken(user.getUsername(), user.getRoles());
		return token;
	}
}
