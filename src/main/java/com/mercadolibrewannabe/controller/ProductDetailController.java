package com.mercadolibrewannabe.controller;

import com.mercadolibrewannabe.model.Product;
import com.mercadolibrewannabe.model.dto.ProductDto;
import com.mercadolibrewannabe.repository.ProductRepository;
import com.mercadolibrewannabe.repository.QuestionRepository;
import com.mercadolibrewannabe.repository.ReviewRepository;
import com.mercadolibrewannabe.utils.FindById;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductDetailController {

	private final ProductRepository productRepository;
	private final ReviewRepository reviewRepository;
	private final QuestionRepository questionRepository;


	public ProductDetailController (ProductRepository productRepository,
	                                ReviewRepository reviewRepository,
	                                QuestionRepository questionRepository) {

		this.productRepository = productRepository;
		this.reviewRepository = reviewRepository;
		this.questionRepository = questionRepository;
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ProductDto detail (@PathVariable String id) {

		Product foundProduct = FindById.using(productRepository, UUID.fromString(id));
		return foundProduct.toDto(reviewRepository::findByProduct, questionRepository::findByProductOrderByCreatedAtDesc);
	}
}
