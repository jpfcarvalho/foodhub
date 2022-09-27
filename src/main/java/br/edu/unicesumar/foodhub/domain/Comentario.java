package br.edu.unicesumar.foodhub.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "comentario")
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
	private LocalDateTime dataPublicacao = LocalDateTime.now();

	@ManyToOne(optional = false)
	@JsonBackReference
	@JsonIgnoreProperties({ "comentarios" })
	@JoinColumn(name = "id_pessoa", nullable = false, insertable = false, updatable = false)
	private Pessoa pessoa;

	@ManyToOne(optional = false)
	@JsonBackReference
	@JsonIgnoreProperties({ "comentarios" })
	@JoinColumn(name = "id_midia", nullable = false, insertable = false, updatable = false)
	private Midia midia;

}
