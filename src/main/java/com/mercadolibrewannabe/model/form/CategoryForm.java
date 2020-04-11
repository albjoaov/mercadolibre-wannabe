package com.mercadolibrewannabe.model.form;

import com.mercadolibrewannabe.model.Category;

import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class CategoryForm {

	@NotBlank
	private String name;

	private String parentCategoryId;

	public Category toModel(Function<UUID, Optional<Category>> categoryLoader) {

		if (parentCategoryId != null) {
			UUID uuid = UUID.fromString(parentCategoryId);

			// TODO: Handle wrong id passed by Client
			Category foundCategory = categoryLoader.apply(uuid).get();
			return new Category(name, foundCategory);

		} else {
			return new Category(name);
		}
	}

	public String getName () {
		return this.name;
	}

	public String getParentCategoryId() {
		return this.parentCategoryId;
	}
}
