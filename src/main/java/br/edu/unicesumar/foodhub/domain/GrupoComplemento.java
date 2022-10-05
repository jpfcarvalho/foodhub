package br.edu.unicesumar.foodhub.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import br.edu.unicesumar.foodhub.converter.BooleanToStringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "grupo_complemento")
@JsonFilter("filterFields")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class GrupoComplemento implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_GRUPO_COMPLEMENTO")
	@SequenceGenerator(name = "S_GRUPO_COMPLEMENTO", sequenceName = "S_GRUPO_COMPLEMENTO", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "obrigatorio")
	@Convert(converter = BooleanToStringConverter.class)
	private Boolean obrigatorio = Boolean.FALSE;

	@NotNull
	@Column(name = "quantidade_minima", nullable = false)
	private Long quantidadeMinima;

	@NotNull
	@Column(name = "quantidade_maxima", nullable = false)
	private Long quantidadeMaxima;

	@NotNull
	@Size(min = 1)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_complemento", nullable = false)
	private List<ProdutoComplemento> produtosComplementos;

}
