package com.mercadolibrewannabe.model.dto;

import com.mercadolibrewannabe.model.Review;

public class ReviewDto {

	private final Integer rating;

	private final String title;

	private final String description;

	public ReviewDto (Review review) {
		this.rating = review.getRating();
		this.title = review.getTitle();
		this.description = review.getDescription();
	}
}
