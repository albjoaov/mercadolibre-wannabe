package com.mercadolibrewannabe.model.dto;

import com.mercadolibrewannabe.model.Feature;

import java.util.Objects;

public class FeatureDto {

	private final String name;

	private final String detail;

	public FeatureDto (Feature feature) {
		this.name = feature.getName();
		this.detail = feature.getDetail();
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FeatureDto that = (FeatureDto) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(detail, that.detail);
	}

	@Override
	public int hashCode () {
		return Objects.hash(name, detail);
	}

	public String getName () {
		return name;
	}

	public String getDetail () {
		return detail;
	}
}
