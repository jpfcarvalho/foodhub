package br.edu.unicesumar.foodhub.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PESSOA")
	@SequenceGenerator(name = "S_PESSOA", sequenceName = "S_PESSOA", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "nome", nullable = false)
	private String nome;

	@NotEmpty
	@Column(name = "sobrenome", nullable = false)
	private String sobrenome;

	@CPF
	@Column(name = "cpf", nullable = false)
	private String cpf;

	@Length(min = 10, max = 11)
	@NotEmpty
	@Column(name = "telefone", nullable = false)
	private String telefone;

	@NotNull
	@Size(min = 1)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	@JsonIgnoreProperties({ "pessoa" })
	@JoinColumn(name = "id_pessoa")
	private List<Endereco> enderecos = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pessoa", nullable = false)
	private List<Pagamento> pagamentos = new ArrayList<>();

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_users", unique = true, updatable = false)
	private Users users;

	@ManyToMany
	@JsonManagedReference
	@JsonIgnoreProperties({ "favoritados" })
	@JoinTable(name = "pessoa_restaurante", joinColumns = { @JoinColumn(name = "id_pessoa") }, inverseJoinColumns = {
			@JoinColumn(name = "id_restaurante") })
	private List<Restaurante> favoritos;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_comentario")
	private List<Comentario> comentarios;

}
