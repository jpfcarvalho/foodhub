package br.edu.unicesumar.foodhub.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
import br.edu.unicesumar.foodhub.converter.ListStringToStringConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "midia")
@JsonFilterFields(of = { "id", "titulo", "descricao", "ativo", "tags", "dataPublicacao", "caminho", "fotoPrincipal",
		"produto.id" })
public class Midia implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_MIDIA")
	@SequenceGenerator(name = "S_MIDIA", sequenceName = "S_MIDIA", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "titulo", nullable = false)
	private String titulo;

	@Column(name = "descricao")
	private String descricao;

	@Builder.Default
	@Column(name = "ativo")
	@Convert(converter = BooleanToStringConverter.class)
	private Boolean ativo = Boolean.TRUE;

	@Builder.Default
	@Column(name = "tags")
	@Convert(converter = ListStringToStringConverter.class)
	private List<String> tags = new ArrayList<>();

	@NotNull
	@Builder.Default
	@Column(name = "data_publicacao", nullable = false)
	private LocalDateTime dataPublicacao = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

	@NotEmpty
	@Column(name = "caminho", nullable = false)
	private String caminho;

	@Builder.Default
	@Column(name = "foto_principal")
	@Convert(converter = BooleanToStringConverter.class)
	private Boolean fotoPrincipal = Boolean.FALSE;

	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JsonBackReference("produto_midia")
	@JsonIgnoreProperties({ "midias" })
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto produto;

	@Builder.Default
	@ManyToMany(mappedBy = "curtidas")
	@JsonIgnoreProperties({ "curtidas" })
	private List<Pessoa> curtidas = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "midia", orphanRemoval = true)
	@JsonManagedReference("midia_comentario")
	@JsonIgnoreProperties({ "midia" })
	private List<Comentario> comentarios = new ArrayList<>();

}
