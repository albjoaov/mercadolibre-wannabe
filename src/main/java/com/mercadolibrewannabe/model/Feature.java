package com.mercadolibrewannabe.model;

import com.mercadolibrewannabe.model.form.FeatureForm;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Embeddable
public class Feature {

	@NotBlank
	@Column (nullable = false)
	private String name;

	@NotBlank
	@Column (nullable = false)
	private String detail;

	/**
	 * @deprecated (Just for framework usages)
	 */
	@Deprecated
	public Feature () {
	}

	public Feature(FeatureForm featureForm) {
		this.name = featureForm.getName();
		this.detail = featureForm.getDetail();
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Feature feature = (Feature) o;
		return name.equals(feature.name) &&
				detail.equals(feature.detail);
	}

	@Override
	public int hashCode () {
		return Objects.hash(name, detail);
	}
}
