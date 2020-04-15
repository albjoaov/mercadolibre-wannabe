package com.mercadolibrewannabe.model;

import com.mercadolibrewannabe.model.dto.CategoryDto;
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
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@EntityListeners (AuditingEntityListener.class)
public class Category {

	@Id
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

	public static Deque<CategoryDto> getCategoryDtoStack(Category category) {
		Deque<Category> categoriesFamilyStack = getAllParentCategories(category);
		return mapCategoryStackToCategoryDtoStack(categoriesFamilyStack);
	}

	private static Deque<Category> getAllParentCategories (Category category) {

		Deque<Category> categoryDtoStack = new ArrayDeque<>();

		while (category != null) {
			categoryDtoStack.push(category);
			category = category.parentCategory;
		}

		return categoryDtoStack;
	}

	private static Deque<CategoryDto> mapCategoryStackToCategoryDtoStack (Deque<Category> categoryStack) {
		return categoryStack.stream().map(CategoryDto::new).collect(Collectors.toCollection(ArrayDeque::new));
	}

	public String getName () {
		return name;
	}
}