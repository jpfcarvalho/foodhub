package br.edu.unicesumar.foodhub.base;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class LoadService<T extends BaseEntity> {

	@Autowired
	private JpaRepository<T, Long> repository;

	public JpaRepository<T, Long> getRepository() {
		return repository;
	}

	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Optional<T> findById(Long id) {
		return repository.findById(id);
	}
}
