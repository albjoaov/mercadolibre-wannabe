package com.mercadolibrewannabe.model.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.List;
import java.util.Set;

@JsonAutoDetect (fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ProductDto {

	private final List<String> photoUrlList;
	private final Deque<CategoryDto> categoryDtoStack;
	private final String name;
	private final BigDecimal value;
	private final Set<FeatureDto> featureDtoSet;
	private final String markdownDescription;
	private final List<QuestionDto> questionDtoList;
	private final ReviewDtoWrapper reviewDtoWrapper;

	public ProductDto (List<String> photoUrlList,
	                   Deque<CategoryDto> categoryDtoStack,
	                   String name,
	                   BigDecimal value,
	                   Set<FeatureDto> featureDtoSet,
	                   String markdownDescription,
	                   List<QuestionDto> questionDtoList,
	                   ReviewDtoWrapper reviewDtoWrapper) {

		this.photoUrlList = photoUrlList;
		this.categoryDtoStack = categoryDtoStack;
		this.name = name;
		this.value = value;
		this.featureDtoSet = featureDtoSet;
		this.markdownDescription = markdownDescription;
		this.questionDtoList = questionDtoList;
		this.reviewDtoWrapper = reviewDtoWrapper;
	}
}
