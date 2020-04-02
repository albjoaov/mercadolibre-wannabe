package com.mercadolibrewannabe.model.validation;

import com.mercadolibrewannabe.model.User;
import com.mercadolibrewannabe.model.form.UserForm;
import com.mercadolibrewannabe.repository.UserRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;

public class UniqueEmailValidator implements Validator {

	private final UserRepository userRepository;

	public UniqueEmailValidator (UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean supports (Class<?> clazz) {
		return UserForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate (Object target, Errors errors) {
		UserForm userForm = (UserForm) target;
		String email = userForm.getEmail();

		Optional<User> userOptional = this.userRepository.findByEmail(email);

		userOptional.ifPresent(e -> {
			errors.rejectValue("email", "This email already has been used");
		});
	}
}
