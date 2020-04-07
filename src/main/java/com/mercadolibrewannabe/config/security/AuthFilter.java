package com.mercadolibrewannabe.config.security;

import com.mercadolibrewannabe.model.User;
import com.mercadolibrewannabe.repository.UserRepository;
import com.mercadolibrewannabe.service.JwtTokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class AuthFilter extends OncePerRequestFilter {

	private final JwtTokenService jwtTokenService;
	private final UserRepository userRepository;

	public AuthFilter (JwtTokenService jwtTokenService, UserRepository userRepository) {
		this.jwtTokenService = jwtTokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal (HttpServletRequest request,
	                                 HttpServletResponse response,
	                                 FilterChain filterChain) throws ServletException, IOException {

		String token = this.retrieveToken(request);

		if (jwtTokenService.isValid(token)) {
			authenticateRequest(token);
		}

		filterChain.doFilter(request, response);
	}

	private String retrieveToken (HttpServletRequest request) {

		String token = request.getHeader("Authorization");

		if (token == null || token.isBlank() || !token.startsWith("Bearer ")) {
			return null;
		}

		// Token without 'Bearer'
		return token.substring(7);
	}

	private void authenticateRequest(String token) {
		UUID userId = jwtTokenService.getUserId(token);
		User user = userRepository.findById(userId).get();

		// Credentials are null because at this point of the code, the password was already checked
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
