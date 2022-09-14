package br.edu.unicesumar.foodhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.foodhub.domain.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	Users findUsersByUsername(String username);

}
