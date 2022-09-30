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
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pedido_complemento_produto")
@JsonFilter("filterFields")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PedidoComplementoProduto implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PEDIDO_COMPLEMENTO_PRODUTO")
	@SequenceGenerator(name = "S_PEDIDO_COMPLEMENTO_PRODUTO", sequenceName = "S_PEDIDO_COMPLEMENTO_PRODUTO", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "valor_complemento", nullable = false)
	private BigDecimal valorComplemento;

	@NotEmpty
	@Column(name = "quantidade", nullable = false)
	private Long quantidade;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_produto_complemento", nullable = false, insertable = false, updatable = false)
	private ProdutoComplemento produtoComplemento;
}
