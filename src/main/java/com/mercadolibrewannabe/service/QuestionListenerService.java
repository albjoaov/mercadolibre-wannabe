package com.mercadolibrewannabe.service;

import com.mercadolibrewannabe.event.QuestionCreatedEvent;
import com.mercadolibrewannabe.model.Email;
import com.mercadolibrewannabe.infra.EmailBodyProvider;
import com.mercadolibrewannabe.model.Question;
import com.mercadolibrewannabe.repository.EmailRepository;
import com.mercadolibrewannabe.utils.Mailer;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class QuestionListenerService {

	private final Mailer mailer;
	private final EmailRepository emailRepository;
	private final EmailBodyProvider emailBodyProvider;

	public QuestionListenerService (Mailer mailer, EmailRepository emailRepository, EmailBodyProvider emailBodyProvider) {
		this.mailer = mailer;
		this.emailRepository = emailRepository;
		this.emailBodyProvider = emailBodyProvider;
	}

	@EventListener
	public void handleQuestionCreation(QuestionCreatedEvent event) {

		Question question = (Question) event.getSource();
		Email email = question.buildEmail(emailBodyProvider);

		emailRepository.save(email);
		mailer.send(question);
	}
}
