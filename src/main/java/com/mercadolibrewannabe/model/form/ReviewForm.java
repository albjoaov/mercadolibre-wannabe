package com.mercadolibrewannabe.model.form;

import com.mercadolibrewannabe.model.Product;
import com.mercadolibrewannabe.model.Review;
import com.mercadolibrewannabe.utils.enums.Rating;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class ReviewForm {

	@NotNull
	@Max(5)
	@Min(1)
	private Integer rating;

	@NotNull
	private String title;

	@NotNull
	@Length(max = 500)
	private String description;

	@NotNull
	private String productId;

	public Integer getRating () {
		return rating;
	}

	public String getTitle () {
		return title;
	}

	public String getDescription () {
		return description;
	}

	public String getProductId () {
		return productId;
	}

	public Review toModel (Function<UUID, Optional<Product>> productLoader) {

		// TODO: Handle wrong id passed by Client
		Product product = productLoader.apply(UUID.fromString(productId)).get();

		Rating ratingEnum = Rating.byValue(this.rating);

		return new Review(ratingEnum, this.title, this.description, product);
	}
}
