package br.edu.unicesumar.foodhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.base.LoadController;
import br.edu.unicesumar.foodhub.domain.Cidade;
import br.edu.unicesumar.foodhub.service.CidadeService;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController extends LoadController<Cidade> {

	@Autowired
	private CidadeService cidadeService;

	@GetMapping("/estado/{idEstado}")
	public ResponseEntity<Page<Cidade>> findCidadesByEstado(@PathVariable(name = "idEstado") Long idEstado,
			Pageable pageable) {
		return ResponseEntity.ok(cidadeService.getCidadesByEstado(idEstado, pageable));
	}

}
