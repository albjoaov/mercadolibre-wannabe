package com.mercadolibrewannabe.model;

import com.mercadolibrewannabe.utils.BCrypter;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends SuperEntity {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 6)
	private String password;

	/**
	 * @deprecated (Just for framework usages)
	 */
	@Deprecated
	public User() { }

	/**
	 * @param email string with a email format, e.g: your_email@example.com
	 * @param hashAlgorithm hashing object
	 */
	public User (@Email @NotBlank String email,
	             @NotNull BCrypter hashAlgorithm) {
		this.email = email;
		this.password = hashAlgorithm.getHashedPassword();
	}
}
