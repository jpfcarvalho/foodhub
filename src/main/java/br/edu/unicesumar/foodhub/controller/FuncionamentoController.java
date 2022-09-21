package br.edu.unicesumar.foodhub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.base.LoadController;
import br.edu.unicesumar.foodhub.domain.Funcionamento;

@RestController
@RequestMapping("/api/funcionamentos")
public class FuncionamentoController extends LoadController<Funcionamento> {

}
