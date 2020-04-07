package com.mercadolibrewannabe.model;

import com.mercadolibrewannabe.utils.BCrypter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
public class User extends SuperEntity implements UserDetails {

	@NotBlank
	@Email
	@Column (unique = true)
	private String email;

	@NotBlank
	@Size(min = 6)
	private String password;

	@ManyToMany
	private Set<Role> roles;

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities () {
		return null;
	}

	@Override
	public String getPassword () {
		return this.password;
	}

	@Override
	public String getUsername () {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired () {
		return true;
	}

	@Override
	public boolean isAccountNonLocked () {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired () {
		return true;
	}

	@Override
	public boolean isEnabled () {
		return true;
	}
}
