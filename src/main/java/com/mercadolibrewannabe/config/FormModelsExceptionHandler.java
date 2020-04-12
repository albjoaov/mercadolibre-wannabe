package com.mercadolibrewannabe.config;

import com.mercadolibrewannabe.model.dto.ApiErrorReturn;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class FormModelsExceptionHandler {

	private final MessageSource messageSource;

	public FormModelsExceptionHandler (MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler ({MethodArgumentNotValidException.class, BindException.class})
	public List<ApiErrorReturn> handle(Exception exception) {

		if (exception instanceof MethodArgumentNotValidException) {
			return getApiErrorResponse(((MethodArgumentNotValidException) exception).getBindingResult().getFieldErrors());

		} else {
			return getApiErrorResponse(((BindException) exception).getBindingResult().getFieldErrors());
		}
	}

	private List<ApiErrorReturn> getApiErrorResponse (List<FieldError> fieldErrors) {

		return fieldErrors.stream().map(fieldError -> {

			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			return new ApiErrorReturn(fieldError.getField(), message);

		}).collect(Collectors.toList());
	}
}
