package br.edu.unicesumar.foodhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.foodhub.domain.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}
