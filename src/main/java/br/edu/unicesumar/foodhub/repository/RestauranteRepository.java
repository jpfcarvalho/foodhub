package br.edu.unicesumar.foodhub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.foodhub.domain.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

	Optional<Restaurante> findRestauranteByUsersUsername(String username);

}
