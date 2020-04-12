package com.mercadolibrewannabe.model;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners (AuditingEntityListener.class)
public class Question {

	@Id
	@GeneratedValue
	@Type (type="uuid-char")
	private UUID id;

	@Column (nullable = false, updatable = false)
	private final LocalDateTime createdAt = LocalDateTime.now();

	@ManyToOne (optional = false)
	@CreatedBy
	private User author;

	@NotNull
	@Column(nullable = false)
	private String title;

	@NotNull
	@ManyToOne(optional = false)
	private Product product;

	/**
	 * @deprecated usage only for frameworks
	 */
	@Deprecated
	public Question () { }

	public Question (@NotNull String title,
	                 @NotNull Product product) {

		Assert.notNull(title);
		Assert.notNull(product);

		this.title = title;
		this.product = product;
	}
}
