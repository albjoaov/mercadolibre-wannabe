package com.mercadolibrewannabe.repository;

import com.mercadolibrewannabe.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}
