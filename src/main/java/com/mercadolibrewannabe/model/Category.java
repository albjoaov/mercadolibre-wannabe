package com.mercadolibrewannabe.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Category extends SuperEntity {

	@NotBlank
	private String name;

	@OneToOne(fetch = FetchType.LAZY)
	private Category parentCategory;

	/**
	 * @deprecated (Just for framework usages)
	 */
	@Deprecated
	public Category() { }

	public Category (String name) {
		this.name = name;
	}

	public Category (String name, Category parentCategory) {
		this.name = name;
		this.parentCategory = parentCategory;
	}
}
