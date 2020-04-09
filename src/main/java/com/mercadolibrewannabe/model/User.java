package com.mercadolibrewannabe.model;

import com.mercadolibrewannabe.utils.BCrypter;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Entity
@EntityListeners (AuditingEntityListener.class)
public class User implements UserDetails {

	@Id
	@Type (type="uuid-char")
	@GeneratedValue
	private UUID id;

	@Column (nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Version
	private Integer version;

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
		return this.roles;
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

	public UUID getId () {
		return id;
	}
}
