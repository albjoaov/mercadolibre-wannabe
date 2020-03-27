package com.mercadolibrewannabe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// Authentication configurations
//	@Override
//	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
//	}

	// Authorization configurations
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/user").permitAll()
				.antMatchers(
						"/v2/api-docs",
						"/swagger-resources/**",
						"/swagger-ui.html",
						"/webjars/**" ,
						/*Probably not needed*/ "/swagger.json")
				.permitAll();
	}

	// Allow Swagger UI requests to static resources
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}
}
