package com.mercadolibrewannabe.controller;

import com.mercadolibrewannabe.model.Product;
import com.mercadolibrewannabe.model.User;
import com.mercadolibrewannabe.model.form.ProductForm;
import com.mercadolibrewannabe.repository.CategoryRepository;
import com.mercadolibrewannabe.repository.ProductRepository;
import com.mercadolibrewannabe.utils.Uploader;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	private final Uploader uploader;


	public ProductController (ProductRepository productRepository,
	                          CategoryRepository categoryRepository,
	                          Uploader uploader) {

		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.uploader = uploader;
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@Valid ProductForm productForm) {

		// @AuthenticatedPrincipal can be used too
		 User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		 Product product = productForm.toModel(principal, uploader, categoryRepository::findById);
		 productRepository.save(product);
	}
}
