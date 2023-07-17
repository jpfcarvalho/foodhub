package br.edu.unicesumar.foodhub.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.unicesumar.foodhub.base.CrudController;
import br.edu.unicesumar.foodhub.domain.Restaurante;
import br.edu.unicesumar.foodhub.domain.Users;
import br.edu.unicesumar.foodhub.repository.RestauranteRepository;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController extends CrudController<Restaurante> {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Override
	@PostMapping("/signup")
	public ResponseEntity<Restaurante> save(@RequestBody Restaurante entity) throws URISyntaxException {
		return super.save(entity);
	}

	@GetMapping("/me")
	public ResponseEntity<Restaurante> getMe(@AuthenticationPrincipal Users user) {
		return ResponseEntity.ok(restauranteRepository.findRestauranteByUsersUsername(user.getUsername())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")));
	}

}
