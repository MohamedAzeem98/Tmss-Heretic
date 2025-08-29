package com.travelmanagementservice.tmss.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String secretkey;
	
	@Value("${jwt.expiration}")
	private long expiration;
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+expiration))
				.signWith(SignatureAlgorithm.HS256,secretkey)
				.compact();
	}
	
	public Claims extractClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secretkey)
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
	
	

}
