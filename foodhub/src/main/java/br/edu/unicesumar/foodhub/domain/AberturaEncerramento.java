package br.edu.unicesumar.foodhub.domain;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "abertura_encerramento")
public class AberturaEncerramento implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ABERTURA_ENCERRAMENTO")
	@SequenceGenerator(name = "S_ABERTURA_ENCERRAMENTO", sequenceName = "S_ABERTURA_ENCERRAMENTO", allocationSize = 1)
	private Long id;

	@NotNull
	@Column(name = "hora_abertura", nullable = false)
	private LocalTime horaAbertura;

	@NotNull
	@Column(name = "hora_encerramento", nullable = false)
	private LocalTime horaEncerramento;

}
