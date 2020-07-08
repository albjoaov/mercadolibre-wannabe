package com.mercadolibrewannabe.model.dto;

import com.mercadolibrewannabe.model.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewDto {

	private final Integer rating;

	private final String title;

	private final String description;

	public ReviewDto (Review review) {
		this.rating = review.getRating();
		this.title = review.getTitle();
		this.description = review.getDescription();
	}

	public static List<ReviewDto> mapToReviewDtoList (List<Review> reviewList) {
		return reviewList.stream().map(ReviewDto::new).collect(Collectors.toList());
	}

	public Integer getRating () {
		return rating;
	}

	public String getTitle () {
		return title;
	}

	public String getDescription () {
		return description;
	}
}
