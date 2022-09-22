package br.edu.unicesumar.foodhub.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import br.edu.unicesumar.foodhub.converter.BooleanToStringConverter;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PEDIDO")
	@SequenceGenerator(name = "S_PEDIDO", sequenceName = "S_PEDIDO", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "texto")
	private String texto;

	@NotNull
	@Column(name = "nota")
	private BigDecimal nota;

	@Column(name = "curtiu")
	@Convert(converter = BooleanToStringConverter.class)
	private Boolean curtiu = Boolean.FALSE;

	@NotEmpty
	@Column(name = "data_publicacao", nullable = false)
	private LocalDateTime dataPublicacao;

}
