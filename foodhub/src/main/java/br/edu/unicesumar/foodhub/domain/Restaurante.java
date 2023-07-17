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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
@Table(name = "restaurante")
@JsonFilterFields(of = { "id", "razaoSocial", "nomeFantasia", "cpfCnpj", "telefone", "endereco.id", "endereco.numero",
		"endereco.complemento", "endereco.cep", "endereco.logradouro", "endereco.bairro", "endereco.cidade.nome",
		"endereco.estado.nome", "endereco.estado.uf", "endereco.coordenadas.latitute", "endereco.coordenadas.longitude",
		"endereco.principal", "endereco.apelido", "categoria.id", "categoria.tipo", "users.id", "users.email",
		"grupos.id", "funcionamento.id", "funcionamento.raioFuncionamentoKm", "funcionamento.aberto",
		"funcionamento.diasFuncionamentos.id", "funcionamento.diasFuncionamentos.diaSemana",
		"funcionamento.diasFuncionamentos.aberturasEncerramentos.id",
		"funcionamento.diasFuncionamentos.aberturasEncerramentos.horaAbertura",
		"funcionamento.diasFuncionamentos.aberturasEncerramentos.horaEncerramento", "pedidos.id" })
public class Restaurante implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_RESTAURANTE")
	@SequenceGenerator(name = "S_RESTAURANTE", sequenceName = "S_RESTAURANTE", allocationSize = 1)
	private Long id;

	@Column(name = "razao_social")
	private String razaoSocial;

	@NotEmpty
	@Column(name = "nome_fantasia", nullable = false)
	private String nomeFantasia;

	@Length(min = 11, max = 14)
	@NotEmpty
	@Column(name = "cpf_cnpj", nullable = false)
	private String cpfCnpj;

	@Length(min = 10, max = 11)
	@NotEmpty
	@Column(name = "telefone", nullable = false)
	private String telefone;

	@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_endereco", nullable = false)
	private Endereco endereco;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_users", unique = true, updatable = false)
	private Users users;

	@OneToMany(mappedBy = "restaurante", orphanRemoval = true)
	@JsonManagedReference("restaurante_grupo")
	@JsonIgnoreProperties({ "restaurante" })
	private List<Grupo> grupos = new ArrayList<>();

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcionamento", nullable = false)
	private Funcionamento funcionamento;

	@OneToMany(mappedBy = "restaurante", orphanRemoval = true)
	@JsonManagedReference("restaurante_pedido")
	@JsonIgnoreProperties({ "restaurante" })
	private List<Pedido> pedidos = new ArrayList<>();

	@ManyToMany(mappedBy = "favoritos")
	@JsonIgnoreProperties({ "favoritos" })
	private List<Pessoa> favoritados = new ArrayList<>();

}
