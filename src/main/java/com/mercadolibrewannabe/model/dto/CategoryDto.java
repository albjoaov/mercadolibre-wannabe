package com.mercadolibrewannabe.model.dto;

import com.mercadolibrewannabe.model.Category;

public class CategoryDto {

	private final String name;

	public CategoryDto (Category category) {
		this.name = category.getName();
	}

	public String getName () {
		return name;
	}
}
