package br.edu.unicesumar.foodhub.base;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

public abstract class LoadController<T extends BaseEntity> {

	@Autowired
	private LoadService<T> service;

	public LoadService<T> getService() {
		return service;
	}

	@GetMapping
	public ResponseEntity<Page<T>> findAll(
			@RequestParam(value = "filter", required = false, defaultValue = StringUtils.EMPTY) String filter,
			Pageable pageable) {
		return ResponseEntity.ok(service.findAll(filter, pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<T> findById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado")));
	}

}
