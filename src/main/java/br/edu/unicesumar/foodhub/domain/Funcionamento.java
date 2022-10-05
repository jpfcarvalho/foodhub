package br.edu.unicesumar.foodhub.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcionamento")
@JsonFilter("filterFields")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Funcionamento implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_FUNCIONAMENTO")
	@SequenceGenerator(name = "S_FUNCIONAMENTO", sequenceName = "S_FUNCIONAMENTO", allocationSize = 1)
	private Long id;

	@Column(name = "raio_funcionamento_km", nullable = false)
	private Long raioFuncionamentoKm;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_funcionamento", nullable = false)
	List<DiaFuncionamento> diasFuncionamentos;

}
