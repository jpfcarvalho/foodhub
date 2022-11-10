package br.edu.unicesumar.foodhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.foodhub.base.CrudService;
import br.edu.unicesumar.foodhub.domain.Grupo;
import br.edu.unicesumar.foodhub.repository.RestauranteRepository;

@Service
public class GrupoService extends CrudService<Grupo> {

	@Autowired
	private UsersService usersService;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Override
	protected void beforeSave(Grupo entity) {
		restauranteRepository.findRestauranteByUsersUsername(usersService.findUsersLogado().getUsername())
				.ifPresentOrElse(restaurante -> entity.setRestaurante(restaurante), () -> {
					throw new AuthorizationServiceException("O Usuario não tem Permissão de criar grupos.");
				});
	}

}
