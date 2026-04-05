package com.example.restapiegitimders1.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {

	 	private final SecretKey key;
	    private final long expirationTime = 1000 * 60 * 60 * 10; // 10 hours

	    public JwtService(@Value("${jwt.secret}") String secret) {
	        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	    }


	    public String extractUsername(String jwt) {
	        return extractAllClaims(jwt).getSubject();
	    }

	    public Claims extractAllClaims(String jwt) {
	        return Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
	    }

	    public boolean isTokenValid(String jwt, UserDetails userDetails) {
	        final String username = extractUsername(jwt);
	        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwt);
	    }

	    private boolean isTokenExpired(String jwt) {
	        return extractAllClaims(jwt).getExpiration().before(new Date());
	    }

	    public String generateToken(String username, Set<String> roles) {
	        return Jwts.builder().subject(username)
	                .claim("roles", roles)
	                .issuedAt(new Date(System.currentTimeMillis()))
	                .expiration(new Date(System.currentTimeMillis() + expirationTime))
	                .signWith(key)
	                .compact();
	    }
}
