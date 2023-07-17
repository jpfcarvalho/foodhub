package br.edu.unicesumar.foodhub.repository;

import br.edu.unicesumar.foodhub.base.CrudRepository;
import br.edu.unicesumar.foodhub.domain.Users;

public interface UsersRepository extends CrudRepository<Users> {

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	Users findUsersByUsername(String username);

}
