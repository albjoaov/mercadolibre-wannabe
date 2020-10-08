package com.mercadolibrewannabe.infra;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class EmailBodyMockProvider implements EmailBodyProvider {

	@Override
	public String get () {
		return "Email body for first tests";
	}
}
