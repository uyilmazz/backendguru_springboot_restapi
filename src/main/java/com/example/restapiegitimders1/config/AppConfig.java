package com.example.restapiegitimders1.config;

import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.restapiegitimders1.entity.User;
import com.example.restapiegitimders1.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

	private final UserRepository userRepository;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return username -> {
			User user = userRepository.findByUsername(username)
					.orElseThrow(() -> new RuntimeException("User not found: " + username));
			
			return new org.springframework.security.core.userdetails.User(
					user.getUsername(),
					user.getPassword(),
					user.getRoles().stream().map(SimpleGrantedAuthority::new)
					.collect(Collectors.toSet())
					);
		};
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider autProvider = new DaoAuthenticationProvider(userDetailsService());
		autProvider.setPasswordEncoder(passwordEncoder());
		return autProvider;
	}
}
