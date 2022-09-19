package br.edu.unicesumar.foodhub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.base.CrudController;
import br.edu.unicesumar.foodhub.domain.Pedido;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController extends CrudController<Pedido> {

}
