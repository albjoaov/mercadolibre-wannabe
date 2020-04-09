package com.mercadolibrewannabe.config.security;

import com.mercadolibrewannabe.model.User;
import com.mercadolibrewannabe.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

	private final UserRepository userRepository;

	public AuthService (UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
		// TODO: Como funciona a checagem de senha? Em memória? Como?
		Optional<User> optionalUser = userRepository.findByEmail(email);
		return optionalUser.orElseThrow(() -> new UsernameNotFoundException("Invalid data"));
		// TODO: Check user enumeration
	}
}
