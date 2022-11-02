package br.edu.unicesumar.foodhub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.unicesumar.foodhub.base.CrudRepository;
import br.edu.unicesumar.foodhub.domain.Cidade;

public interface CidadeRepository extends CrudRepository<Cidade> {

	Page<Cidade> findByEstadoId(Long id, Pageable pageable);

}
