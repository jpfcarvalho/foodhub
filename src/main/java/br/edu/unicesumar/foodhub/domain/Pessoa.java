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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

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

	@NotEmpty
	@Column(name = "telefone", nullable = false)
	private String telefone;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pessoa", nullable = false)
	private List<Endereco> enderecos = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pessoa", nullable = false)
	private List<Pagamento> pagamentos = new ArrayList<>();

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_users", unique = true, updatable = false)
	private Users users;

}
