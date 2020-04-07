package com.mercadolibrewannabe.controller;

import com.mercadolibrewannabe.model.form.UserForm;
import com.mercadolibrewannabe.service.JwtTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenService jwtTokenService;

	public AuthController (AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenService = jwtTokenService;
	}

	@PostMapping
	public ResponseEntity<String> authenticate(@RequestBody @Valid UserForm userForm) {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = userForm.toAuthToken();

		try {
			Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			String token = jwtTokenService.generate(authentication);
			String fullToken = "Bearer " + token;
			return ResponseEntity.ok(fullToken);

		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
