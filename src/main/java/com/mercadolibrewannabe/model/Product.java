package com.mercadolibrewannabe.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Currency;
import org.hibernate.validator.constraints.Length;
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
import javax.validation.constraints.Max;
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
	@JoinColumn
	@CollectionTable(name = "photo", joinColumns = @JoinColumn(name = "product_id"))
	@Size(min = 1)
	private List<Photo> photoList;

	@NotBlank
	private String name;

	@NotBlank
	@DecimalMin(value = "0", inclusive = false)
	private BigDecimal value;

	@Min(0)
	private Long amount;

	@ElementCollection
	@JoinColumn
	@CollectionTable(name = "features", joinColumns = @JoinColumn(name = "product_id"))
	@Size(min = 3)
	private Set<Features> featuresSet;

	@NotBlank
	@Size(max = 1000)
	private String description;

	@ManyToOne
	@NotNull
	private Category category;

	@ManyToOne
	@NotNull
	private User user;
}
