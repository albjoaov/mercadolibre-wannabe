package com.mercadolibrewannabe.controller;

import com.mercadolibrewannabe.model.User;
import com.mercadolibrewannabe.model.form.UserForm;
import com.mercadolibrewannabe.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserRepository userRepository;

	public UserController (UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public void create(@Valid @RequestBody UserForm userForm) {
		User user = userForm.toDomain();
		userRepository.save(user);
	}
}
