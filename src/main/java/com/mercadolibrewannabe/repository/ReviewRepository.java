package com.mercadolibrewannabe.repository;

import com.mercadolibrewannabe.model.Product;
import com.mercadolibrewannabe.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

	List<Review> findByProduct (@NotNull Product product);
}
