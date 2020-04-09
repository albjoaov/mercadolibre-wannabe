package com.mercadolibrewannabe.model;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@EntityListeners (AuditingEntityListener.class)
public class Role implements GrantedAuthority {

	private String authority;

	@Id
	@GeneratedValue
	@Type (type="uuid-char")
	private UUID id;

	@Column (nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Version
	private Integer version;

	// Equals and HashCode

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Role role = (Role) o;
		return authority.equals(role.authority);
	}

	@Override
	public int hashCode () {
		return Objects.hash(authority);
	}

	// Getters and Setters

	@Override
	public String getAuthority () {
		return authority;
	}
}
