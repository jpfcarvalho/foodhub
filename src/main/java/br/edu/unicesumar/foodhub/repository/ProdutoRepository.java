package br.edu.unicesumar.foodhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.foodhub.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
