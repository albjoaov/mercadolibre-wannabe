package com.mercadolibrewannabe.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Photo {

	@Column (nullable = false)
	@NotBlank
	private String url;

	/**
	 * @deprecated (Just for framework usages)
	 */
	@Deprecated
	public Photo () {
	}

	public Photo (String url) {
		this.url = url;
	}
}
