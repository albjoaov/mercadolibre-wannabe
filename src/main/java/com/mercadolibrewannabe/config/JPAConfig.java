package com.mercadolibrewannabe.config;

import com.mercadolibrewannabe.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JPAConfig {

	@Bean
	public AuditorAware<User> auditorProvider() {
		return new AuditorAwareImpl();
	}
}
