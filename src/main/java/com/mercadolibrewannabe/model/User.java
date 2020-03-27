package com.mercadolibrewannabe.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends SuperEntity {

	@NotNull
	@NotBlank
	@Email
	private String email;

	@NotNull
	@NotBlank
	@Size(min = 6)
	private String password;

	@Deprecated
	/** @deprecated Constructor only for frameworks */
	public User() { }

	public User (String email, String password) {
		this.email = email;
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public String getEmail () {
		return email;
	}

	public void setEmail (String email) {
		this.email = email;
	}

	public String getPassword () {
		return password;
	}

	public void setPassword (String password) {
		this.password = password;
	}
}
