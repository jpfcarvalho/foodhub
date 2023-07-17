package br.edu.unicesumar.foodhub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.base.LoadController;
import br.edu.unicesumar.foodhub.domain.AberturaEncerramento;

@RestController
@RequestMapping("/api/aberturas-ancerramentos")
public class AberturaEncerramentoController extends LoadController<AberturaEncerramento> {

}
