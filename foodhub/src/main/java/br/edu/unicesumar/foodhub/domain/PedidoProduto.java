package br.edu.unicesumar.foodhub.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pedido_produto")
public class PedidoProduto implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PEDIDO_COMPLEMENTO_PRODUTO")
	@SequenceGenerator(name = "S_PEDIDO_COMPLEMENTO_PRODUTO", sequenceName = "S_PEDIDO_COMPLEMENTO_PRODUTO", allocationSize = 1)
	private Long id;

	@NotNull
	@Column(name = "preco_produto", nullable = false)
	private BigDecimal precoProduto;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto produto;

	@Transient
	private Long quantidade = 1L;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_pedido_produto", nullable = false)
	private List<PedidoComplementoProduto> complementos = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_midia")
	private Midia midia;

	public BigDecimal getPrecoComplementos() {
		return complementos.stream()
				.map(pc -> pc.getValorComplemento().multiply(BigDecimal.valueOf(pc.getQuantidade())))
				.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}

	public static PedidoProduto of(PedidoProduto pedidoProduto) {

		PedidoProduto newPedidoProduto = new PedidoProduto();
		newPedidoProduto.setId(null);
		newPedidoProduto.setPrecoProduto(pedidoProduto.getPrecoProduto());
		newPedidoProduto.setProduto(pedidoProduto.getProduto());
		pedidoProduto.getComplementos().forEach(
				complemento -> newPedidoProduto.getComplementos().add(PedidoComplementoProduto.of(complemento)));
		newPedidoProduto.setMidia(pedidoProduto.getMidia());

		return newPedidoProduto;
	}

}
