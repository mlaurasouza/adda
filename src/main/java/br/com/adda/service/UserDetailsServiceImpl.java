package br.com.adda.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.adda.data.UserDetailData;
import br.com.adda.model.User;
import br.com.adda.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository repository;

	public UserDetailsServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findByEmail(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
		}

		return new UserDetailData(user);
	}
}