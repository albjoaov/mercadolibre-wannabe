package com.mercadolibrewannabe.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude (NON_NULL)
public class ApiErrorReturn {

	private String field;
	private String errorMessage;

	public ApiErrorReturn (String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ApiErrorReturn (String field, String errorMessage) {
		this.field = field;
		this.errorMessage = errorMessage;
	}

	public String getField () {
		return field;
	}

	public void setField (String field) {
		this.field = field;
	}

	public String getErrorMessage () {
		return errorMessage;
	}

	public void setErrorMessage (String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
