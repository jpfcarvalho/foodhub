package br.edu.unicesumar.foodhub.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cidade")
public class Cidade implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_CIDADE")
	@SequenceGenerator(name = "S_CIDADE", sequenceName = "S_CIDADE", allocationSize = 1)
	private Long id;

	@NotNull
	@NotBlank
	@Column(name = "nome", nullable = false)
	private String nome;

	@ManyToOne(cascade = CascadeType.DETACH, optional = false)
	@JoinColumn(name = "id_estado", nullable = false)
	private Estado estado;

}
