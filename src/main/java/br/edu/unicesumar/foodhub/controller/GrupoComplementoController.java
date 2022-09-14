package br.edu.unicesumar.foodhub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.base.CrudController;
import br.edu.unicesumar.foodhub.domain.GrupoComplemento;

@RestController
@RequestMapping("/api/complementos")
public class GrupoComplementoController extends CrudController<GrupoComplemento> {

}
