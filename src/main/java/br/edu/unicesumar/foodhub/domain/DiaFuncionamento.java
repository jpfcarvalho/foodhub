package br.edu.unicesumar.foodhub.domain;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import br.edu.unicesumar.foodhub.converter.SemanaToLongConverter;
import br.edu.unicesumar.foodhub.domain.enuns.Semana;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dia_funcionamento")
public class DiaFuncionamento implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_DIA_FUNCIONAMENTO")
	@SequenceGenerator(name = "S_DIA_FUNCIONAMENTO", sequenceName = "S_DIA_FUNCIONAMENTO", allocationSize = 1)
	private Long id;

	@NotNull
	@Convert(converter = SemanaToLongConverter.class)
	@Column(name = "dia_semana", nullable = false)
	private Semana diaSemana;

	@Size(min = 1)
	@NotNull
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_dia_funcionamento", nullable = false)
	private List<AberturaEncerramento> aberturasEncerramentos = new ArrayList<>();

}
