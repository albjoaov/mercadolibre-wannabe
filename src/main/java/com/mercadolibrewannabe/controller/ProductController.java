package com.mercadolibrewannabe.controller;

import com.mercadolibrewannabe.model.Product;
import com.mercadolibrewannabe.model.User;
import com.mercadolibrewannabe.model.form.ProductForm;
import com.mercadolibrewannabe.repository.ProductRepository;
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

	public ProductController (ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@Valid ProductForm productForm) {

		 User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		 Product product = productForm.toModel(principal);
		 productRepository.save(product);
	}
}
