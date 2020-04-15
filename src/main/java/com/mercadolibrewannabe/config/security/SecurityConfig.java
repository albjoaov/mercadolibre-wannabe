package com.mercadolibrewannabe.config.security;

import com.mercadolibrewannabe.repository.UserRepository;
import com.mercadolibrewannabe.service.JwtTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final AuthService authService;
	private final JwtTokenService jwtTokenService;
	private final UserRepository userRepository;

	public SecurityConfig (AuthService authService, JwtTokenService jwtTokenService, UserRepository userRepository) {
		this.authService = authService;
		this.jwtTokenService = jwtTokenService;
		this.userRepository = userRepository;
	}

	// Authentication Config
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(authService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager () throws Exception {
		return super.authenticationManager();
	}

	// Authorization Config
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/auth").permitAll()
				.antMatchers(HttpMethod.POST,"/user").permitAll()
				.antMatchers(HttpMethod.GET,"/product").permitAll()
				.anyRequest().authenticated()
			.and()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.addFilterBefore(new AuthFilter(jwtTokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
	}

	// Static resources Config (JS, CSS, Images...)
	@Override
	public void configure (WebSecurity web) {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}
}
