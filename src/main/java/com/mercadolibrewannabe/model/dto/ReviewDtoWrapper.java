package com.mercadolibrewannabe.model.dto;

import java.util.List;

public class ReviewDtoWrapper {

	private final int reviewListSize;
	private final double ratingAverage;
	private final List<ReviewDto> reviewDtoList;

	public ReviewDtoWrapper (int reviewListSize, double ratingAverage, List<ReviewDto> reviewDtoList) {
		this.reviewListSize = reviewListSize;
		this.ratingAverage = ratingAverage;
		this.reviewDtoList = reviewDtoList;
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
