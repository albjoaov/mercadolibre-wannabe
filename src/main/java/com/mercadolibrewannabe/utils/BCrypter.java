package com.mercadolibrewannabe.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BCrypter {

	@NotBlank
	@Size(min = 6)
	private String unhashedPassword;

	public BCrypter (String unhashedPassword) {
		this.unhashedPassword = unhashedPassword;
	}

	public String getHashedPassword () {
		return new BCryptPasswordEncoder().encode(this.unhashedPassword);
	}
}
