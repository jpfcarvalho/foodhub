package br.edu.unicesumar.foodhub.base;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

public abstract class CrudController<T extends BaseEntity> extends LoadController<T> {

	public CrudService<T> getCrudService() {
		return (CrudService<T>) super.getService();
	}

	@PostMapping
	public ResponseEntity<T> save(@RequestBody T entity) throws URISyntaxException {
		T newEntity = getCrudService().save(entity);
		return ResponseEntity.created(new URI("/" + newEntity.getId())).body(newEntity);
	}

	@PutMapping("/{id}")
	public ResponseEntity<T> update(@PathVariable(name = "id") Long id, @RequestBody T entity) {
		validarRegistro(id);
		entity.setId(id);
		return ResponseEntity.ok(getCrudService().save(entity));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) {
		validarRegistro(id);
		getCrudService().deleteById(id);
		return ResponseEntity.ok().build();
	}

	private void validarRegistro(Long id) {
		if (!getService().getRepository().existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado");
		}
	}
}
