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

public class ProductForm {

	@Size(min = 1)
	@NotNull
	private List<MultipartFile> photoList;

	@NotBlank
	private String name;

	@DecimalMin(value = "0", inclusive = false)
	private BigDecimal value;

	@Min(0)
	private Long amount;

	@Size(min = 3)
	private Set<FeaturesForm> featuresFormSet;

//	@NotNull
//	private FeaturesForm featuresForm;

	@NotBlank
	@Size(max = 1000)
	private String description;

	@NotNull
	private String categoryId;

	public Product toModel (User principal) {
		return new Product();
	}

	// SETTERS

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

	public String getDescription () {
		return description;
	}

	public String getCategoryId () {
		return categoryId;
	}

	public void setPhotoList (List<MultipartFile> photoList) {
		this.photoList = photoList;
	}

	public void setName (String name) {
		this.name = name;
	}

	public void setValue (BigDecimal value) {
		this.value = value;
	}

	public void setAmount (Long amount) {
		this.amount = amount;
	}

	public void setDescription (String description) {
		this.description = description;
	}

	public void setCategoryId (String categoryId) {
		this.categoryId = categoryId;
	}

	public Set<FeaturesForm> getFeaturesFormSet () {
		return featuresFormSet;
	}

	public void setFeaturesFormSet (Set<FeaturesForm> featuresFormSet) {
		this.featuresFormSet = featuresFormSet;
	}
}
