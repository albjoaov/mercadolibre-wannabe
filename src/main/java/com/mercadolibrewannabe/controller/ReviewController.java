package com.mercadolibrewannabe.controller;

import com.mercadolibrewannabe.model.Review;
import com.mercadolibrewannabe.model.form.ReviewForm;
import com.mercadolibrewannabe.repository.ProductRepository;
import com.mercadolibrewannabe.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/review")
public class ReviewController {

	private final ReviewRepository reviewRepository;
	private final ProductRepository productRepository;

	public ReviewController (ReviewRepository reviewRepository, ProductRepository productRepository) {
		this.reviewRepository = reviewRepository;
		this.productRepository = productRepository;
	}

	@PostMapping
	@ResponseStatus (code = HttpStatus.CREATED)
	public void create(@RequestBody @Valid ReviewForm reviewForm) {

		Review review = reviewForm.toModel(productRepository::findById);
		reviewRepository.save(review);
	}

}
