package br.edu.unicesumar.foodhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.edu.unicesumar.foodhub.domain.Users;

@NoRepositoryBean
public interface UsersRepository<T extends Users> extends JpaRepository<T, Long> {

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	T findUsersByUsername(String username);

}
