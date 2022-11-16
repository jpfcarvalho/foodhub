package br.edu.unicesumar.foodhub.dto;

import java.util.ArrayList;
import java.util.List;

import br.edu.unicesumar.foodhub.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedDTO {

	private Long idRestaurante;

	private List<ProdutoDTO> produtos = new ArrayList<>();

	public static FeedDTO of(Long id, List<Produto> produtos) {

		List<ProdutoDTO> produtosDTO = new ArrayList<>();

		FeedDTO feed = new FeedDTO();
		feed.setIdRestaurante(id);
		produtos.forEach(produto -> produtosDTO.add(ProdutoDTO.of(produto)));
		feed.setProdutos(produtosDTO);

		return feed;
	}

}
