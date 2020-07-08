package com.mercadolibrewannabe.model.dto;

import com.mercadolibrewannabe.model.Review;

import java.util.List;

import static com.mercadolibrewannabe.model.dto.ReviewDto.mapToReviewDtoList;

public class ReviewDtoWrapper {

	private final int reviewListSize;
	private final double ratingAverage;
	private final List<ReviewDto> reviewDtoList;

	public ReviewDtoWrapper (List<Review> reviewList) {
		this.reviewListSize = reviewList.size();
		this.ratingAverage = reviewList.stream().mapToInt(Review::getRating).average().orElse(0);
		this.reviewDtoList = mapToReviewDtoList(reviewList);
	}

	public int getReviewListSize () {
		return reviewListSize;
	}

	public double getRatingAverage () {
		return ratingAverage;
	}

	public List<ReviewDto> getReviewDtoList () {
		return reviewDtoList;
	}
}
