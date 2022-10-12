package br.edu.unicesumar.foodhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estado")
public class Estado implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ESTADO")
	@SequenceGenerator(name = "S_ESTADO", sequenceName = "S_ESTADO", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@NotEmpty
	@Length(min = 2, max = 2)
	@Column(name = "uf", nullable = false, unique = true)
	private String uf;

}
