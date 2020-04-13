package com.mercadolibrewannabe.repository;

import com.mercadolibrewannabe.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
