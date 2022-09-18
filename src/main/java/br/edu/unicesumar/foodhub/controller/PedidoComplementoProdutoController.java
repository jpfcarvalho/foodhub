package br.edu.unicesumar.foodhub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.base.CrudController;
import br.edu.unicesumar.foodhub.domain.PedidoComplementoProduto;

@RestController
@RequestMapping("/api/pedido-complemento-produtos")
public class PedidoComplementoProdutoController extends CrudController<PedidoComplementoProduto> {

}
