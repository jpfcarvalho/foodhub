package br.edu.unicesumar.foodhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.base.LoadController;
import br.edu.unicesumar.foodhub.domain.Funcionamento;
import br.edu.unicesumar.foodhub.service.FuncionamentoService;

@RestController
@RequestMapping("/api/funcionamentos")
public class FuncionamentoController extends LoadController<Funcionamento> {

	@Autowired
	private FuncionamentoService funcionamentoService;

	@PutMapping("/abertura")
	public ResponseEntity<Boolean> abertura() {
		return ResponseEntity.ok(funcionamentoService.abertura());
	}

}
