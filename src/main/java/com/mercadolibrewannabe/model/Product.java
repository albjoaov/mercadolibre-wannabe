package com.mercadolibrewannabe.model;

import io.jsonwebtoken.lang.Assert;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@EntityListeners (AuditingEntityListener.class)
public class Product {

	@Id
	@Type (type="uuid-char")
	@GeneratedValue
	private UUID id;

	@Column (nullable = false, updatable = false)
	private final LocalDateTime createdAt = LocalDateTime.now();

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Version
	private Integer version;

	@ElementCollection
	@CollectionTable(name = "photo", joinColumns = @JoinColumn(name = "product_id"))
	@Size(min = 1)
	@NotNull
	private List<Photo> photoList;

	@NotBlank
	@Column(nullable = false)
	private String name;

	@DecimalMin(value = "0", inclusive = false)
	@Column(nullable = false)
	private BigDecimal value;

	@Min(0)
	@NotNull
	@Column(nullable = false)
	private Long amount;

	@ElementCollection
	@CollectionTable(name = "features", joinColumns = @JoinColumn(name = "product_id"))
	@Size(min = 3)
	@NotNull
	@Column(nullable = false)
	private Set<Feature> featureSet;

	@NotBlank
	@Size(max = 1000)
	@Column(nullable = false)
	private String description;

	@ManyToOne(optional = false)
	@NotNull
	private Category category;

	@ManyToOne(optional = false)
	@NotNull
	private User user;

	/**
	 * @deprecated (Just for framework usages)
	 */
	@Deprecated
	public Product () { }

	public Product (List<Photo> photoList,
	                String name,
	                BigDecimal value,
	                Long amount,
	                Set<Feature> featureSet,
	                String description,
	                Category category,
	                User user) {

		Assert.notNull(photoList);
		Assert.notEmpty(photoList);
		Assert.hasText(name);
		Assert.isTrue(value.doubleValue() > 0);
		Assert.isTrue(amount >= 0);
		Assert.isTrue(featureSet.size() >= 3);
		Assert.hasText(description);
		Assert.isTrue(description.length() <= 1000);
		Assert.notNull(category);
		Assert.notNull(user);

		this.name = name;
		this.value = value;
		this.amount = amount;
		this.description = description;
		this.user = user;

		this.category = category;
		this.featureSet = featureSet;
		this.photoList = photoList;
	}
}
