package com.mercadolibrewannabe.event;

import org.springframework.context.ApplicationEvent;

public class QuestionCreatedEvent extends ApplicationEvent {

	/**
	 * Create a new {@code ApplicationEvent}.
	 *
	 * @param source the object on which the event initially occurred or with
	 *               which the event is associated (never {@code null})
	 */
	public QuestionCreatedEvent (Object source) {
		super(source);
	}
}
