package br.edu.unicesumar.foodhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import br.edu.unicesumar.foodhub.base.CrudService;
import br.edu.unicesumar.foodhub.config.auth.jwt.Jwt;
import br.edu.unicesumar.foodhub.config.auth.jwt.JwtTool;
import br.edu.unicesumar.foodhub.domain.Users;
import br.edu.unicesumar.foodhub.dto.sign.SignIn;
import br.edu.unicesumar.foodhub.repository.UsersRepository;

public abstract class UsersService<T extends Users & BaseEntity> extends CrudService<T> implements UserDetailsService {

	@Lazy
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTool jwtTokenTool;

	@Autowired
	private UsersRepository<T> usersRepository;

	@Override
	public T loadUserByUsername(String username) throws UsernameNotFoundException {
		return usersRepository.findUsersByUsername(username);
	}

	public Jwt signIn(SignIn signIn) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(signIn.getUsername(), signIn.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		Users userDetails = (Users) authentication.getPrincipal();

		return jwtTokenTool.generateToken(userDetails);

	}

	public T signUp(T entity) {
		entity.setUsername(entity.getEmail());
		if (usersRepository.existsByUsername(entity.getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already taken!");
		}

		if (usersRepository.existsByEmail(entity.getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already taken!");
		}

		String pass = passwordEncoder.encode(entity.getPassword());

		entity.setPassword(pass);

		return usersRepository.save(entity);
	}

}
