package com.example.restapiegitimders1.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapiegitimders1.dto.LoginRequest;
import com.example.restapiegitimders1.dto.LoginResponse;
import com.example.restapiegitimders1.dto.RegisterRequest;
import com.example.restapiegitimders1.dto.RegisterResponse;
import com.example.restapiegitimders1.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
		long userId = authService.register(request.username(), request.password(), Set.of("ROLE_USER"));
		return ResponseEntity.ok(new RegisterResponse(userId));
	}
	
	/*@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
		String token = authService.login(request.username(), request.password());
		return ResponseEntity.ok(new LoginResponse(token));
	}*/
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
		String token = authService.login(request.username(), request.password());
		return ResponseEntity.ok(new LoginResponse(token));
	}
}
