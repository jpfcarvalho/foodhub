package br.edu.unicesumar.foodhub.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.unicesumar.foodhub.base.CrudService;
import br.edu.unicesumar.foodhub.config.auth.jwt.Jwt;
import br.edu.unicesumar.foodhub.config.auth.jwt.JwtTool;
import br.edu.unicesumar.foodhub.domain.Users;
import br.edu.unicesumar.foodhub.dto.sign.SignIn;
import br.edu.unicesumar.foodhub.repository.UsersRepository;

@Service
public class UsersService extends CrudService<Users> implements UserDetailsService {

	@Lazy
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTool jwtTokenTool;

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public Users loadUserByUsername(String username) throws UsernameNotFoundException {
		return usersRepository.findUsersByUsername(username);
	}

	public Jwt signIn(SignIn signIn) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(signIn.getUsername(), signIn.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		Users userDetails = (Users) authentication.getPrincipal();

		if (!userDetails.getRoles().stream().anyMatch(r -> r.getAuthority().equals(signIn.getRoles()))) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não autorizado!");
		}

		return jwtTokenTool.generateToken(userDetails);

	}

	public Users signUp(Users entity) {
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

	public Users findUsersLogado() {

		return Optional.ofNullable((Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.orElseThrow(() -> new AuthorizationServiceException("Usuario não encontrado"));

	}

}
