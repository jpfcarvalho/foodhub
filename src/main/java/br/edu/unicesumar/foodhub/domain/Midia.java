package br.edu.unicesumar.foodhub.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import br.edu.unicesumar.foodhub.converter.BooleanToStringConverter;
import br.edu.unicesumar.foodhub.converter.ListStringToStringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "midia")
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

	@Column(name = "ativo")
	@Convert(converter = BooleanToStringConverter.class)
	private Boolean ativo = Boolean.TRUE;

	@Column(name = "tags")
	@Convert(converter = ListStringToStringConverter.class)
	private List<String> tags;

	@NotEmpty
	@Column(name = "data_publicacao", nullable = false)
	private LocalDateTime dataPublicacao = LocalDateTime.now();

	@NotEmpty
	@Column(name = "caminho", nullable = false)
	private String caminho;

	@Column(name = "foto_principal")
	@Convert(converter = BooleanToStringConverter.class)
	private Boolean fotoPrincipal = Boolean.TRUE;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto produto;

}
