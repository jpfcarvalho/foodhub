package br.edu.unicesumar.foodhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.foodhub.base.LoadService;
import br.edu.unicesumar.foodhub.domain.Cidade;
import br.edu.unicesumar.foodhub.repository.CidadeRepository;

@Service
public class CidadeService extends LoadService<Cidade> {

	@Autowired
	private CidadeRepository cidadeRepository;

	public Page<Cidade> getCidadesByEstado(Long idEstado, Pageable pageable) {
		return cidadeRepository.findByEstadoId(idEstado, pageable);
	}

}
