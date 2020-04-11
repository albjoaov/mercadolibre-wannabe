package com.mercadolibrewannabe.model.form;

import com.mercadolibrewannabe.model.Category;
import com.mercadolibrewannabe.model.Feature;
import com.mercadolibrewannabe.model.Photo;
import com.mercadolibrewannabe.model.Product;
import com.mercadolibrewannabe.model.User;
import com.mercadolibrewannabe.utils.Uploader;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductForm {

	@Size(min = 1)
	@NotNull
	private List<MultipartFile> multipartPhotoList;

	@NotBlank
	private String name;

	@NotNull
	@DecimalMin(value = "0", inclusive = false)
	private BigDecimal value;

	@Min(0)
	@NotNull
	private Long amount;

	@Size(min = 3)
	@NotNull
	private List<FeatureForm> featureForms;

	@NotBlank
	@Size(max = 1000)
	private String description;

	@NotBlank
	private String categoryId;

	public Product toModel (User principal, Uploader uploader, Function<UUID, Optional<Category>> categoryLoader) {
		List<Photo> photoList = getPhotoListFromMultipartFileList(uploader);
		HashSet<Feature> featureSet = getFeatureSetFromFeatureFormList();

		// TODO: Handle wrong id passed by Client
		Category category = categoryLoader.apply(UUID.fromString(categoryId)).get();

		return new Product(photoList, this.name, this.value,
							this.amount, featureSet, this.description,
							category, principal);
	}

	private HashSet<Feature> getFeatureSetFromFeatureFormList () {
		return this.featureForms
				.stream()
				.map(Feature::new)
				.collect(Collectors.toCollection(HashSet::new));
	}

	private List<Photo> getPhotoListFromMultipartFileList (Uploader uploader) {
		List<String> photoUrlList = uploader.upload(this.multipartPhotoList);

		return photoUrlList
				.stream()
				.map(Photo::new)
				.collect(Collectors.toList());
	}

	public void setMultipartPhotoList (List<MultipartFile> multipartPhotoList) {
		this.multipartPhotoList = multipartPhotoList;
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

	public void setFeatureForms (List<FeatureForm> featureForms) {
		this.featureForms = featureForms;
	}

	public void setDescription (String description) {
		this.description = description;
	}

	public void setCategoryId (String categoryId) {
		this.categoryId = categoryId;
	}

	public List<FeatureForm> getFeatureForms () {
		return featureForms;
	}
}
