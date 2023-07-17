package br.edu.unicesumar.foodhub.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.foodhub.base.CrudService;
import br.edu.unicesumar.foodhub.domain.Midia;
import br.edu.unicesumar.foodhub.domain.Produto;
import br.edu.unicesumar.foodhub.dto.MidiaDTO;

@Service
public class MidiaService extends CrudService<Midia> {

	@Autowired
	private StorageService storageService;

	@Autowired
	private EntityManager em;

	public Midia saveMidia(MidiaDTO midiaDTO) {

		Produto produto = em.find(Produto.class, midiaDTO.getIdProduto());

		String caminho = storageService.uploadFile(midiaDTO.getMidia(),
				produto.getGrupo().getRestaurante().getNomeFantasia());

		Midia midia = Midia.builder().titulo(midiaDTO.getTitulo()).descricao(midiaDTO.getDescricao())
				.ativo(midiaDTO.getAtivo()).tags(midiaDTO.getTags()).dataPublicacao(midiaDTO.getDataPublicacao())
				.caminho(caminho).fotoPrincipal(midiaDTO.getFotoPrincipal()).produto(produto).build();

		return save(midia);
	}

	public byte[] downloadMidia(String caminho) {

		return storageService.downloadFile(caminho);

	}

	@Override
	protected void beforeDelete(Midia entity) {
		storageService.deleteFile(entity.getCaminho());
	}
}
