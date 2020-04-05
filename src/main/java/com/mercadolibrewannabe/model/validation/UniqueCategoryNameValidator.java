package com.mercadolibrewannabe.model.validation;

import com.mercadolibrewannabe.model.Category;
import com.mercadolibrewannabe.model.form.CategoryForm;
import com.mercadolibrewannabe.repository.CategoryRepository;
import org.springframework.validation.Validator;

import java.util.Optional;

public class UniqueCategoryNameValidator extends UniqueValidator<CategoryForm, Category> implements Validator {

	private final CategoryRepository categoryRepository;

	public UniqueCategoryNameValidator (CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	protected Optional<Category> findEntityByField (CategoryForm form) {
		return categoryRepository.findByName(form.getName());
	}

	@Override
	protected Class<CategoryForm> getFormClass () {
		return CategoryForm.class;
	}

	@Override
	protected String getErrorMessage () {
		return "Another category already has this name";
	}

	@Override
	protected String getInvalidFieldName () {
		return "name";
	}

	@Override
	protected String getErrorCode () {
		return null;
	}
}
