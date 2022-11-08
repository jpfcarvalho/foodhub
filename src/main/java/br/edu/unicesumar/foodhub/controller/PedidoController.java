package br.edu.unicesumar.foodhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.base.CrudController;
import br.edu.unicesumar.foodhub.domain.Pedido;
import br.edu.unicesumar.foodhub.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController extends CrudController<Pedido> {

	@Autowired
	private PedidoService pedidoService;

	@PutMapping("/atualizar-status/{idPedido}")
	public ResponseEntity<Void> update(@PathVariable(name = "idPedido") Long idPedido,
			@RequestParam(value = "cancelar", required = false, defaultValue = "false") Boolean cancelar) {
		pedidoService.atualizarStatusPedido(idPedido, cancelar);
		return ResponseEntity.ok().build();
	}

}
