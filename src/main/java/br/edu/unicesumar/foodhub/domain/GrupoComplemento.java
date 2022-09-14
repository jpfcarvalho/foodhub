package br.edu.unicesumar.foodhub.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "grupo_complemento")
public class GrupoComplemento implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_GRUPO_COMPLEMENTO")
	@SequenceGenerator(name = "S_GRUPO_COMPLEMENTO", sequenceName = "S_GRUPO_COMPLEMENTO", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "nome", nullable = false)
	private String nome;

	@NotEmpty
	@Column(name = "obrigatorio", nullable = false)
	private Boolean obrigatorio = Boolean.FALSE;

	@NotEmpty
	@Column(name = "quantidade_minima", nullable = false)
	private Long quantidade_minima;

	@NotEmpty
	@Column(name = "quantidade_maxima", nullable = false)
	private Long quantidade_maxima;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_complemento")
	private List<ProdutoComplemento> produtos;

}
