package br.edu.unicesumar.foodhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.foodhub.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
