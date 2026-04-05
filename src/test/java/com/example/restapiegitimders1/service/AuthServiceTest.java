package com.example.restapiegitimders1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.example.restapiegitimders1.entity.User;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("AuthService Unit Tests")
public class AuthServiceTest {

	@Mock
	private UserService userService;
	
	@Mock
	private AuthenticationManager authenticationManager;
	
	@Mock
	private JwtService jwtService;
	
	@InjectMocks
	private AuthService authService;
	
	@Test
	@DisplayName("Should login successfully")
	void shouldLoginSuccessfully() {
		String username = "testuser";
		String password = "testpass";
		
		when(userService.loadByUsername(username)).thenReturn(new User());
		
		authService.login(username, password);
		
		ArgumentCaptor<UsernamePasswordAuthenticationToken> captor = ArgumentCaptor.forClass(UsernamePasswordAuthenticationToken.class); // void methodlar için. Akışta özellikle belirli bir nokta
		// kontrol edilmek isteniyor ise ArgumentCaptor kullanımı mantıklı olur.
		verify(authenticationManager).authenticate(captor.capture());
		
		UsernamePasswordAuthenticationToken authToken = captor.getValue();
		assertEquals(username, authToken.getPrincipal());
		assertEquals(password, authToken.getCredentials());
	}
}
