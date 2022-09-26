package br.edu.unicesumar.foodhub.controller;

import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.base.CrudController;
import br.edu.unicesumar.foodhub.domain.Restaurante;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController extends CrudController<Restaurante> {

	@Override
	@PostMapping("/signup")
	public ResponseEntity<Restaurante> save(@RequestBody Restaurante entity) throws URISyntaxException {
		return super.save(entity);
	}

}
