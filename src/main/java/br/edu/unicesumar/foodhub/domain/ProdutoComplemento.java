package br.edu.unicesumar.foodhub.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "produto_complemento")
public class ProdutoComplemento implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PRODUTO_COMPLEMENTO")
	@SequenceGenerator(name = "S_PRODUTO_COMPLEMENTO", sequenceName = "S_PRODUTO_COMPLEMENTO", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@NotEmpty
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;

	@NotEmpty
	@Column(name = "quantidade_minima", nullable = false)
	private Long quantidade_minima;

	@NotEmpty
	@Column(name = "quantidade_maxima", nullable = false)
	private Long quantidade_maxima;

	@NotEmpty
	@Column(name = "ativo", nullable = false)
	private Boolean ativo = Boolean.TRUE;

}