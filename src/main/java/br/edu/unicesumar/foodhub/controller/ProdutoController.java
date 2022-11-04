package br.edu.unicesumar.foodhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.base.CrudController;
import br.edu.unicesumar.foodhub.domain.Produto;
import br.edu.unicesumar.foodhub.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController extends CrudController<Produto> {

	@Autowired
	private ProdutoService produtoService;

	@PutMapping("/atualizar-disponibilidade/{idProduto}")
	public ResponseEntity<Void> update(@PathVariable(name = "idProduto") Long idProduto) {
		produtoService.atualizarDisponibilidadeProduto(idProduto);
		return ResponseEntity.ok().build();
	}

}
