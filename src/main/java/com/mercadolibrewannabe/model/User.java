package com.mercadolibrewannabe.model;

import com.mercadolibrewannabe.utils.BCrypter;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners (AuditingEntityListener.class)
public class User {

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

	public UUID getId () {
		return id;
	}

	public void setId (UUID id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt () {
		return createdAt;
	}

	public void setCreatedAt (LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt () {
		return updatedAt;
	}

	public void setUpdatedAt (LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getVersion () {
		return version;
	}

	public void setVersion (Integer version) {
		this.version = version;
	}
}
