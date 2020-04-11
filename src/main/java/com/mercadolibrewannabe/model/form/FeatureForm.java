package com.mercadolibrewannabe.model.form;

import javax.validation.constraints.NotBlank;

public class FeatureForm {

	@NotBlank
	private String name;

	@NotBlank
	private String detail;

	public void setName (String name) {
		this.name = name;
	}

	public void setDetail (String detail) {
		this.detail = detail;
	}

	public String getName () {
		return name;
	}

	public String getDetail () {
		return detail;
	}

}
