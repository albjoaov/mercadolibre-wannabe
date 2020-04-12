package com.mercadolibrewannabe.repository;

import com.mercadolibrewannabe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
