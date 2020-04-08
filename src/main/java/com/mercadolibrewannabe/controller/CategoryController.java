package com.mercadolibrewannabe.controller;

import com.mercadolibrewannabe.model.Category;
import com.mercadolibrewannabe.model.form.CategoryForm;
import com.mercadolibrewannabe.model.validation.UniqueCategoryNameValidator;
import com.mercadolibrewannabe.repository.CategoryRepository;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private final CategoryRepository categoryRepository;

	public CategoryController (CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		 webDataBinder.addValidators(new UniqueCategoryNameValidator(categoryRepository));
	}

	@PostMapping
	public void create(@Valid @RequestBody CategoryForm categoryForm) {
		Category category = categoryForm.toModel(categoryRepository::findById);
		categoryRepository.save(category);
	}


}
