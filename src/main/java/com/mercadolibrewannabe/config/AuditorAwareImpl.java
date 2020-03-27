package com.mercadolibrewannabe.config;

import com.mercadolibrewannabe.model.User;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<User> {

	@Override
	public Optional<User> getCurrentAuditor () {
		return Optional.empty();
		// TODO: When AUTH is ready, change this return
	}
}
