package com.mercadolibrewannabe.model;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners (AuditingEntityListener.class)
public class Category {

	@Id
	@Type (type="uuid-char")
	@GeneratedValue
	@Type(type="uuid-char")
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
	@Column(unique = true)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private Category parentCategory;

	/**
	 * @deprecated (Just for framework usages)
	 */
	@Deprecated
	public Category() { }

	public Category (String name) {
		this.name = name;
	}

	public Category (String name, Category parentCategory) {
		this.name = name;
		this.parentCategory = parentCategory;
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
