package com.mercadolibrewannabe.model.dto;

import com.mercadolibrewannabe.model.Question;

import java.time.LocalDateTime;

public class QuestionDto {

	private final LocalDateTime createdAt;

	private final String authorEmail;

	private final String title;

	public QuestionDto (Question question) {
		this.createdAt = question.getCreatedAt();
		this.authorEmail = question.getAuthor().getUsername();
		this.title = question.getTitle();
	}
}
