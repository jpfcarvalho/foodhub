package br.edu.unicesumar.foodhub.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.base.CrudController;
import br.edu.unicesumar.foodhub.config.auth.Roles;
import br.edu.unicesumar.foodhub.domain.Pessoa;
import br.edu.unicesumar.foodhub.service.UsersService;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController extends CrudController<Pessoa> {

	@Autowired
	private UsersService usersService;

	@Override
	@PostMapping("/signup")
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa entity) throws URISyntaxException {
		entity.getUsers().setUsername(entity.getUsers().getEmail());
		entity.getUsers().getRoles().add(Roles.ROLE_CLIENTE);
		entity.setUsers(usersService.signUp(entity.getUsers()));
		return super.save(entity);
	}

}
