package br.edu.unicesumar.foodhub.dto;

import java.math.BigDecimal;

import br.edu.unicesumar.foodhub.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal valor;
	private String urlMidia;

	public static ProdutoDTO of(Produto produto) {

		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setId(produto.getId());
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setDescricao(produto.getDescricao());
		produtoDTO.setValor(produto.getValor());
		produtoDTO.setUrlMidia(produto.getMidias().stream().findFirst().get().getCaminho());

		return produtoDTO;
	}

}
