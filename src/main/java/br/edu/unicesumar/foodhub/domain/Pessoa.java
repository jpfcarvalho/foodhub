package br.edu.unicesumar.foodhub.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import br.edu.unicesumar.foodhub.config.filter.JsonFilterFields;
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
@JsonFilterFields(of = { "id", "nome", "sobrenome", "cpf", "telefone", "enderecos.id", "enderecos.numero",
		"enderecos.complemento", "enderecos.cep", "enderecos.logradouro", "enderecos.bairro", "enderecos.cidade.nome",
		"enderecos.estado.nome", "enderecos.estado.uf", "enderecos.coordenadas.latitute",
		"enderecos.coordenadas.longitude", "enderecos.principal", "enderecos.apelido", "pagamentos.id", "users.id",
		"users.email" })
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
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("pessoa_endereco")
	@JsonIgnoreProperties({ "pessoa" })
	private List<Endereco> enderecos = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	@JsonManagedReference("pessoa_pagamento")
	@JsonIgnoreProperties({ "pessoa" })
	private List<Pagamento> pagamentos = new ArrayList<>();

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_users", unique = true, updatable = false)
	private Users users;

	@ManyToMany
	@JsonIgnoreProperties({ "favoritados" })
	@JoinTable(name = "pessoa_restaurante", joinColumns = { @JoinColumn(name = "id_pessoa") }, inverseJoinColumns = {
			@JoinColumn(name = "id_restaurante") })
	private List<Restaurante> favoritos = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa", orphanRemoval = true)
	@JsonManagedReference("pessoa_comentario")
	@JsonIgnoreProperties({ "pessoa" })
	private List<Comentario> comentarios = new ArrayList<>();

	@ManyToMany
	@JsonIgnoreProperties({ "curtidas" })
	@JoinTable(name = "pessoa_midia", joinColumns = { @JoinColumn(name = "id_pessoa") }, inverseJoinColumns = {
			@JoinColumn(name = "id_midia") })
	private List<Midia> curtidas = new ArrayList<>();

}
