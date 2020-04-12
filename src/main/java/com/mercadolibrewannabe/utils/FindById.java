package com.mercadolibrewannabe.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.UUID;

@Component
@PropertySource("classpath:messages.properties")
public class FindById {

	private static String invalidIdMessage;

	public static <T> T using (JpaRepository<T, UUID> repository, UUID id) {

		Assert.notNull(id);

		Optional<T> entityOptional = repository.findById(id);

		return entityOptional.orElseThrow(() -> new IllegalArgumentException(invalidIdMessage));
	}

	@Value("${id.invalid}")
	public void setInvalidIdMessage (String invalidIdMessage) {
		FindById.invalidIdMessage = invalidIdMessage;
	}
}
