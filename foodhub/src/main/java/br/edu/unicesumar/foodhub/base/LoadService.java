package br.edu.unicesumar.foodhub.base;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.perplexhub.rsql.RSQLJPASupport;

public abstract class LoadService<T extends BaseEntity> {

	@Autowired
	private CrudRepository<T> repository;

	public CrudRepository<T> getRepository() {
		return repository;
	}

	public Page<T> findAll(String filter, Pageable pageable) {
		if (filter.isEmpty()) {
			return repository.findAll(pageable);
		}
		return repository.findAll(RSQLJPASupport.toSpecification(filter), pageable);
	}

	public Optional<T> findById(Long id) {
		return repository.findById(id);
	}
}
