package br.edu.unicesumar.foodhub.domain;

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

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "restaurante")
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

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "id_endereco", nullable = false)
	private Endereco endereco;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_users", unique = true, updatable = false)
	private Users users;

	@OneToMany(orphanRemoval = true)
	@JsonManagedReference
	@JsonIgnoreProperties({ "restaurante" })
	@JoinColumn(name = "id_restaurante", nullable = false, insertable = false, updatable = false)
	private List<Grupo> grupos;

}
