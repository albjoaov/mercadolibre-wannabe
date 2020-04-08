package com.mercadolibrewannabe.model.validation;

import com.mercadolibrewannabe.model.User;
import com.mercadolibrewannabe.model.form.UserForm;
import com.mercadolibrewannabe.repository.UserRepository;
import org.springframework.validation.Validator;

import java.util.Optional;

public class UniqueEmailValidator extends UniqueValidator<UserForm, User> implements Validator {

	private final UserRepository userRepository;

	public UniqueEmailValidator (UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	protected Optional<User> findEntityByField (UserForm form) {
		return this.userRepository.findByEmail(form.getEmail());
	}

	@Override
	protected Class<UserForm> getFormClass () {
		return UserForm.class;
	}

	@Override
	protected String getErrorMessage () {
		return "This email already has been used";
	}

	@Override
	protected String getInvalidFieldName () {
		return "email";
	}

	@Override
	protected String getErrorCode () {
		return null;
	}
}
