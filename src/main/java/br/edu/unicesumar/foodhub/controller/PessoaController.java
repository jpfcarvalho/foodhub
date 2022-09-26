package br.edu.unicesumar.foodhub.controller;

import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.base.CrudController;
import br.edu.unicesumar.foodhub.domain.Pessoa;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController extends CrudController<Pessoa> {

	@Override
	@PostMapping("/signup")
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa entity) throws URISyntaxException {
		return super.save(entity);
	}

}
