package br.edu.unicesumar.foodhub.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import br.edu.unicesumar.foodhub.converter.BooleanToStringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "produto")
@JsonFilter("filterFields")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Produto implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PRODUTO")
	@SequenceGenerator(name = "S_PRODUTO", sequenceName = "S_PRODUTO", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@NotNull
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;

	@Column(name = "ativo")
	@Convert(converter = BooleanToStringConverter.class)
	private Boolean ativo = Boolean.TRUE;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_produto", nullable = false)
	private List<GrupoComplemento> gruposComplementos = new ArrayList<>();

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JsonBackReference("grupo_produto")
	@JsonIgnoreProperties({ "produtos" })
	@JoinColumn(name = "id_grupo", nullable = false)
	private Grupo grupo;

	@OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
	@JsonManagedReference("produto_midia")
	@JsonIgnoreProperties({ "produto" })
	@JoinColumn(name = "id_produto", nullable = false)
	private List<Midia> midias;
}
