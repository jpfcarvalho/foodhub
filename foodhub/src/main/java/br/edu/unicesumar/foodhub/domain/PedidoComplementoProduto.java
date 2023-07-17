package br.edu.unicesumar.foodhub.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pedido_complemento_produto")
public class PedidoComplementoProduto implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PEDIDO_COMPLEMENTO_PRODUTO")
	@SequenceGenerator(name = "S_PEDIDO_COMPLEMENTO_PRODUTO", sequenceName = "S_PEDIDO_COMPLEMENTO_PRODUTO", allocationSize = 1)
	private Long id;

	@NotNull
	@Column(name = "valor_complemento", nullable = false)
	private BigDecimal valorComplemento;

	@NotNull
	@Column(name = "quantidade", nullable = false)
	private Long quantidade;

	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_produto_complemento", nullable = false)
	private ProdutoComplemento produtoComplemento;

	public static PedidoComplementoProduto of(PedidoComplementoProduto pedidoComplementoProduto) {

		PedidoComplementoProduto newPedidoComplementoProduto = new PedidoComplementoProduto();
		newPedidoComplementoProduto.setId(null);
		newPedidoComplementoProduto.setValorComplemento(pedidoComplementoProduto.getValorComplemento());
		newPedidoComplementoProduto.setQuantidade(pedidoComplementoProduto.getQuantidade());
		newPedidoComplementoProduto.setProdutoComplemento(pedidoComplementoProduto.getProdutoComplemento());

		return newPedidoComplementoProduto;
	}
}
