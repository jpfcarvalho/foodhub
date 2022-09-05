package br.edu.unicesumar.foodhub.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import br.edu.unicesumar.foodhub.config.auth.Roles;
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
@Table(name = "restaurante")
public class Restaurante extends Users implements BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ENDERECO")
	private Long id;

	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
	@JsonProperty(access = Access.READ_ONLY)
	private Set<Roles> roles = new HashSet<>();

	@Email
	@Column(name = "email", unique = true, updatable = false)
	private String email;

	@NotEmpty
	@Column(name = "username", unique = true, updatable = false)
	private String username;

	@NotEmpty
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@Column(name = "razaoSocial")
	private String razaoSocial;

	@NotEmpty
	@Column(name = "nomeFantasia", nullable = false)
	private String nomeFantasia;

	@CPF
	@CNPJ
	@Column(name = "cpfCnpj", nullable = false)
	private String cpfCnpj;

	@NotEmpty
	@Column(name = "telefone", nullable = false)
	private String telefone;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "id_endereco", nullable = false)
	private Endereco endereco;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;

	@Override
	public void addRoles(Roles role) {
		roles.add(role);
	}

	@Override
	public void removeRoles(Roles role) {
		roles.remove(role);

	}

}
