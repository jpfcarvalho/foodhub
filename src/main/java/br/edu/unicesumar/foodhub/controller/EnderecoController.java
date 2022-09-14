package br.edu.unicesumar.foodhub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.base.CrudController;
import br.edu.unicesumar.foodhub.domain.Endereco;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController extends CrudController<Endereco> {

}
