package br.edu.unicesumar.foodhub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.unicesumar.foodhub.domain.Produto;
import br.edu.unicesumar.foodhub.domain.Restaurante;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("SELECT p.grupo.restaurante FROM Produto p WHERE p.id = :idProduto")
	Optional<Restaurante> findRestaurante(@Param("idProduto") Long idProduto);

}
