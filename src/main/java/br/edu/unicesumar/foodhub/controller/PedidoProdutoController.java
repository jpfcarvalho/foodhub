package br.edu.unicesumar.foodhub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.base.CrudController;
import br.edu.unicesumar.foodhub.domain.PedidoProduto;

@RestController
@RequestMapping("/api/pedidos-produtos")
public class PedidoProdutoController extends CrudController<PedidoProduto> {

}
