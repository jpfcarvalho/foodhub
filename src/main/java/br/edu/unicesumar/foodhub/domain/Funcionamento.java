package br.edu.unicesumar.foodhub.domain;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcionamento")
public class Funcionamento implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_FUNCIONAMENTO")
	@SequenceGenerator(name = "S_FUNCIONAMENTO", sequenceName = "S_FUNCIONAMENTO", allocationSize = 1)
	private Long id;

	private LocalTime abertura;

	private LocalTime fechamento;

}
