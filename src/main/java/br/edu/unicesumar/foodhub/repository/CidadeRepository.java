package br.edu.unicesumar.foodhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.foodhub.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
