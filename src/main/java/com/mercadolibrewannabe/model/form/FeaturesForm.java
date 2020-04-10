package com.mercadolibrewannabe.model.form;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class FeaturesForm {

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

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FeaturesForm that = (FeaturesForm) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(detail, that.detail);
	}

	@Override
	public int hashCode () {
		return Objects.hash(name, detail);
	}
}
