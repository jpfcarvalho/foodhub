package br.edu.unicesumar.foodhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.foodhub.domain.Funcionamento;

public interface FuncionamentoRepository extends JpaRepository<Funcionamento, Long> {

}
