package com.mercadolibrewannabe.controller;

import com.mercadolibrewannabe.event.QuestionCreatedEvent;
import com.mercadolibrewannabe.model.Product;
import com.mercadolibrewannabe.model.Question;
import com.mercadolibrewannabe.model.User;
import com.mercadolibrewannabe.model.form.QuestionForm;
import com.mercadolibrewannabe.repository.ProductRepository;
import com.mercadolibrewannabe.repository.QuestionRepository;
import com.mercadolibrewannabe.utils.FindById;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class QuestionController {

	private final QuestionRepository questionRepository;
	private final ProductRepository productRepository;
	private final ApplicationEventPublisher publisher;

	public QuestionController (QuestionRepository questionRepository, ProductRepository productRepository, ApplicationEventPublisher publisher) {
		this.questionRepository = questionRepository;
		this.productRepository = productRepository;
		this.publisher = publisher;
	}

	@PostMapping(value = "/product/{id}/question")
	@ResponseStatus(HttpStatus.CREATED)
	public void create (@PathVariable String id, @RequestBody QuestionForm questionForm, @AuthenticationPrincipal User author) {

		Product product = FindById.using(productRepository, UUID.fromString(id));
		Question question = questionForm.toModel(product, author);

		questionRepository.save(question);
		publisher.publishEvent(question.toEvent());
	}
}
