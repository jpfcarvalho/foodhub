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
@Table(name = "produto")
public class Produto implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PRODUTO")
	@SequenceGenerator(name = "S_PRODUTO", sequenceName = "S_PRODUTO", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "nome", nullable = false)
	private String nome;

	@NotEmpty
	@Column(name = "descricao", nullable = false)
	private String descricao;

	@NotEmpty
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;

	@NotEmpty
	@Column(name = "ativo", nullable = false)
	private Boolean ativo;
}
