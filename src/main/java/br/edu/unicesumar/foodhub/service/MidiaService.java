package br.edu.unicesumar.foodhub.service;

import org.springframework.stereotype.Service;

import br.edu.unicesumar.foodhub.base.CrudService;
import br.edu.unicesumar.foodhub.domain.Midia;

@Service
public class MidiaService extends CrudService<Midia> {

	@Override
	protected void beforeSave(Midia midia) {

	}
}
