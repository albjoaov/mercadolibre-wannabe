package com.mercadolibrewannabe.service;

import com.mercadolibrewannabe.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtTokenService {

	@Value("${jwt.expiration.local}")
	private String expirationTimeInMillis;

	private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public String generate (Authentication authentication) {

		User loggedUser = (User) authentication.getPrincipal();
		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + Long.parseLong(expirationTimeInMillis));

		String jws = Jwts.builder()
				.setIssuer("API MercadoLibreWannabe")
				.setSubject(loggedUser.getId().toString())
				.setIssuedAt(now)
				.setExpiration(expirationDate)
				.signWith(KEY)
				.compact();

		assertToken(jws);
		return jws;
	}

	public boolean isValid(String token) {
		try {
			assertToken(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void assertToken(String token) {
		Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
	}

	public UUID getUserId (String token) {
		Claims body = Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
		return UUID.fromString(body.getSubject());
	}
}
