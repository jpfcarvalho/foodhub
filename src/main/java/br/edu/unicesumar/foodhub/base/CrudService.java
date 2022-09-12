package br.edu.unicesumar.foodhub.base;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CrudService<T extends BaseEntity> {

	@Autowired
	private JpaRepository<T, Long> repository;

	public JpaRepository<T, Long> getRepository() {
		return repository;
	}

	public T save(T entity) {

		if (entity.isNew()) {
			beforeInsert(entity);
		} else {
			beforeUpdate(entity);
		}
		beforeSave(entity);
		return repository.save(entity);
	}

	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Optional<T> findById(Long id) {
		return repository.findById(id);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	protected void beforeUpdate(T entity) {

	}

	protected void beforeInsert(T entity) {

	}

	protected void beforeSave(T entity) {

	}

}
