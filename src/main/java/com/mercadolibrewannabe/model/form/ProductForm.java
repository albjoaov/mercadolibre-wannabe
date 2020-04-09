package com.mercadolibrewannabe.model.form;

import com.mercadolibrewannabe.model.Product;
import com.mercadolibrewannabe.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ProductForm {

	@Size (min = 1)
	private List<MultipartFile> photoList;

	@NotBlank
	private String name;

	@NotBlank
	@DecimalMin (value = "0", inclusive = false)
	private BigDecimal value;

	@Min (0)
	private Long amount;

	@Size(min = 3)
	private Set<FeaturesForm> featuresSet;

	@NotBlank
	@Size(max = 1000)
	private String description;

	@NotNull
	private UUID categoryId;

	public Product toModel (User principal) {
		return new Product();
	}

	// GETTERS

	public List<MultipartFile> getPhotoList () {
		return photoList;
	}

	public String getName () {
		return name;
	}

	public BigDecimal getValue () {
		return value;
	}

	public Long getAmount () {
		return amount;
	}

	public Set<FeaturesForm> getFeaturesSet () {
		return featuresSet;
	}

	public String getDescription () {
		return description;
	}

	public UUID getCategoryId () {
		return categoryId;
	}
}
