package br.edu.unicesumar.foodhub.repository;

import java.util.Optional;

import br.edu.unicesumar.foodhub.base.CrudRepository;
import br.edu.unicesumar.foodhub.domain.Restaurante;

public interface RestauranteRepository extends CrudRepository<Restaurante> {

	Optional<Restaurante> findRestauranteByUsersUsername(String username);

}
