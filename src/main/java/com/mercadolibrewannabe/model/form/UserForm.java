package com.mercadolibrewannabe.model.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mercadolibrewannabe.model.User;
import com.mercadolibrewannabe.utils.BCrypter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class UserForm {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 6)
	private String password;

	public User toDomain () {
		return new User(this.email, new BCrypter(this.password));
	}

	public UsernamePasswordAuthenticationToken toAuthToken() {
		return new UsernamePasswordAuthenticationToken(this.email, this.password);
	}

	public String getEmail () {
		return email;
	}

	public String getPassword () {
		return password;
	}

}
