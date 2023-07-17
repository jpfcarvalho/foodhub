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
import br.edu.unicesumar.foodhub.domain.Pessoa;
import br.edu.unicesumar.foodhub.domain.Users;
import br.edu.unicesumar.foodhub.repository.PessoaRepository;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController extends CrudController<Pessoa> {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	@PostMapping("/signup")
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa entity) throws URISyntaxException {
		return super.save(entity);
	}

	@GetMapping("/me")
	public ResponseEntity<Pessoa> getMe(@AuthenticationPrincipal Users user) {
		return ResponseEntity.ok(pessoaRepository.findPessoaByUsersUsername(user.getUsername())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")));
	}

}
