package com.mercadolibrewannabe.model;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class SuperEntity {

	@Id
	@Type(type="uuid-char")
	@GeneratedValue
	private UUID id;

	@Column (nullable = false, updatable = false)
	@CreatedDate
	protected LocalDateTime createdAt;

	@Column(nullable = false)
	@LastModifiedDate
	protected LocalDateTime updatedAt;

	@Version
	protected Integer version;

	public UUID getId() {
		return id;
	}

	public LocalDateTime getCreatedAt () {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt () {
		return updatedAt;
	}

	public Integer getVersion() {
		return version;
	}

	public void setId (UUID id) {
		this.id = id;
	}

	public void setCreatedAt (LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt (LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setVersion (Integer version) {
		this.version = version;
	}
}
