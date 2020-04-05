package com.mercadolibrewannabe.model.validation;

import com.mercadolibrewannabe.model.SuperEntity;
import com.mercadolibrewannabe.model.form.AbstractForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

public abstract class UniqueValidator<F extends AbstractForm, E extends SuperEntity> implements Validator {

	protected abstract Optional<E> findEntityByField(F form);

	protected abstract Class<F> getFormClass();

	protected abstract String getErrorCode ();

	protected abstract String getErrorMessage ();

	protected abstract String getInvalidFieldName ();

	@Override
	public boolean supports (Class<?> clazz) {
		return getFormClass().isAssignableFrom(clazz);
	}

	@Override
	public void validate (Object target, Errors errors) {

		Optional<E> optionalEntity = findEntityByField((F) target);

		String errorCode = getErrorCode();
		String errorMessage = getErrorMessage();
		String invalidFieldName = getInvalidFieldName();

		optionalEntity.ifPresent(entity -> errors.rejectValue(invalidFieldName, errorCode, errorMessage));
	}

}
