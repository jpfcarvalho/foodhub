package br.edu.unicesumar.foodhub.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import br.edu.unicesumar.foodhub.config.filter.JsonFilterFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "comentario")
@JsonFilterFields(of = { "id", "texto", "nota", "dataPublicacao", "pessoa.id", "midia.id" })
public class Comentario implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_COMENTARIO")
	@SequenceGenerator(name = "S_COMENTARIO", sequenceName = "S_COMENTARIO", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "texto", nullable = false)
	private String texto;

	@Column(name = "nota")
	private BigDecimal nota;

	@NotNull
	@Column(name = "data_publicacao", nullable = false)
	private LocalDateTime dataPublicacao = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JsonBackReference("pessoa_comentario")
	@JsonIgnoreProperties({ "comentarios" })
	@JoinColumn(name = "id_pessoa", nullable = false, updatable = false)
	private Pessoa pessoa;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JsonBackReference("midia_comentario")
	@JsonIgnoreProperties({ "comentarios" })
	@JoinColumn(name = "id_midia", nullable = false, updatable = false)
	private Midia midia;

}
