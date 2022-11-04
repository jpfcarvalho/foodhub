package br.edu.unicesumar.foodhub.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import br.edu.unicesumar.foodhub.config.filter.JsonFilterFields;
import br.edu.unicesumar.foodhub.converter.BooleanToStringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "produto")
@JsonFilterFields(of = { "id", "nome", "descricao", "valor", "ativo", "grupo.id", "gruposComplementos.id",
		"gruposComplementos.nome", "gruposComplementos.obrigatorio", "gruposComplementos.quantidadeMinima",
		"gruposComplementos.quantidadeMaxima", "gruposComplementos.produtosComplementos.id",
		"gruposComplementos.produtosComplementos.nome", "gruposComplementos.produtosComplementos.descricao",
		"gruposComplementos.produtosComplementos.valor", "gruposComplementos.produtosComplementos.quantidadeMinima",
		"gruposComplementos.produtosComplementos.quantidadeMaxima", "gruposComplementos.produtosComplementos.ativo",
		"midias.id" })
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

	@NotNull
	@ManyToOne(optional = false)
	@JsonBackReference("grupo_produto")
	@JsonIgnoreProperties({ "produtos" })
	@JoinColumn(name = "id_grupo", nullable = false)
	private Grupo grupo;

	@OneToMany(mappedBy = "produto", orphanRemoval = true)
	@JsonManagedReference("produto_midia")
	@JsonIgnoreProperties({ "produto" })
	private List<Midia> midias = new ArrayList<>();
}
