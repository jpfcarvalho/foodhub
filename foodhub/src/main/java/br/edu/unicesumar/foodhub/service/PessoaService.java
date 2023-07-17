package br.edu.unicesumar.foodhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.foodhub.base.CrudService;
import br.edu.unicesumar.foodhub.config.auth.Roles;
import br.edu.unicesumar.foodhub.domain.Pessoa;

@Service
public class PessoaService extends CrudService<Pessoa> {

	@Autowired
	private UsersService usersService;

	@Override
	protected void beforeInsert(Pessoa entity) {
		entity.getUsers().setUsername(entity.getUsers().getEmail());
		entity.getUsers().getRoles().add(Roles.ROLE_CLIENTE);
		entity.setUsers(usersService.signUp(entity.getUsers()));
	}

}
