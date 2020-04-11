package com.mercadolibrewannabe.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners (AuditingEntityListener.class)
public class Review {

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

	@Min (1)
	@Max (5)
	@NotNull
	@Column(nullable = false)
	private Long rating;

	@NotNull
	@Column(nullable = false)
	private String title;

	@Length (max = 500)
	@NotNull
	@Column(nullable = false, length = 500)
	private String description;

	@NotNull
	@ManyToOne(optional = false)
	private Product product;

	public Review (Long rating,
	               String title,
	               String description,
	               Product product) {

		Assert.notNull(rating);
		Assert.isTrue(rating >= 1 && rating <=5, "the rating must be between 1 and 5");
		Assert.hasText(title);
		Assert.hasText(description);
		Assert.isTrue(description.length() <= 500, "The description max length is 500");
		Assert.notNull(product);

		this.rating = rating;
		this.title = title;
		this.description = description;
		this.product = product;
	}
}
