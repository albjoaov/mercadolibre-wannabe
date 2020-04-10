package com.mercadolibrewannabe.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Photo {
	@Column (nullable = false)
	private String url;
}
