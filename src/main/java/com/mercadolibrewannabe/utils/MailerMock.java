package com.mercadolibrewannabe.utils;

import com.mercadolibrewannabe.model.Question;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile ("dev")
public class MailerMock implements Mailer {

	@Override
	public void send (Question question) {
		System.out.println("Sending mail with: \n" + question.toString());
	}
}
