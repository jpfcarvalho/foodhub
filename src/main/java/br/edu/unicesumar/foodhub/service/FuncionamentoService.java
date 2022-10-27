package br.edu.unicesumar.foodhub.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.foodhub.base.LoadService;
import br.edu.unicesumar.foodhub.domain.Funcionamento;
import br.edu.unicesumar.foodhub.domain.Restaurante;
import br.edu.unicesumar.foodhub.domain.Users;
import br.edu.unicesumar.foodhub.repository.RestauranteRepository;

@Service
public class FuncionamentoService extends LoadService<Funcionamento> {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private UsersService usersService;

	public void abertura() {
		Users userLogado = usersService.findUsersLogado();
		Optional<Restaurante> restauranteOpt = restauranteRepository
				.findRestauranteByUsersUsername(userLogado.getUsername());
		restauranteOpt.ifPresentOrElse(restaurante -> {
			restaurante.getFuncionamento().setAberto(!restaurante.getFuncionamento().getAberto());
			restauranteRepository.save(restaurante);
		}, () -> {
			throw new AuthorizationServiceException("O Usuario não tem Permissão de abetura");
		});

	}

}
