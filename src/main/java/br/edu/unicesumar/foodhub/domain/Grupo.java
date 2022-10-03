package br.edu.unicesumar.foodhub.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "grupo")
@JsonFilter("filterFields")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Grupo implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_GRUPO")
	@SequenceGenerator(name = "S_GRUPO", sequenceName = "S_GRUPO", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "nome", nullable = false)
	private String nome;

	@OneToMany(mappedBy = "grupo", orphanRemoval = true)
	@JsonManagedReference("grupo_produto")
	@JsonIgnoreProperties({ "grupo" })
	private List<Produto> produtos = new ArrayList<>();

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JsonBackReference("restaurante_grupo")
	@JsonIgnoreProperties({ "grupos" })
	@JoinColumn(name = "id_restaurante", nullable = false)
	private Restaurante restaurante;
}
