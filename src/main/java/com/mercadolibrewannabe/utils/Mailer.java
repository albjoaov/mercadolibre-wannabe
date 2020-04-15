package com.mercadolibrewannabe.utils;

import com.mercadolibrewannabe.model.Question;

public interface Mailer {

	void send(Question question);
}
