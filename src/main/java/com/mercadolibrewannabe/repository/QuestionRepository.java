package com.mercadolibrewannabe.repository;

import com.mercadolibrewannabe.model.Product;
import com.mercadolibrewannabe.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

	List<Question> findByProductOrderByCreatedAtDesc(Product product);
}
