package com.mercadolibrewannabe.service;

import com.mercadolibrewannabe.event.QuestionCreatedEvent;
import com.mercadolibrewannabe.model.Question;
import com.mercadolibrewannabe.utils.Mailer;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class QuestionListenerService {

	public final Mailer mailer;

	public QuestionListenerService (Mailer mailer) {
		this.mailer = mailer;
	}

	@EventListener
	public void handleQuestionCreation(QuestionCreatedEvent event) {
		mailer.send((Question) event.getSource());
	}
}
