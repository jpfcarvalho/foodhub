package br.edu.unicesumar.foodhub.repository;

import java.util.Optional;

import br.edu.unicesumar.foodhub.base.CrudRepository;
import br.edu.unicesumar.foodhub.domain.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa> {

	Optional<Pessoa> findPessoaByUsersUsername(String username);

}
