package com.mercadolibrewannabe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Category extends SuperEntity {

	@NotBlank
	@Column(unique = true)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
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
