package br.edu.unicesumar.foodhub.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import br.edu.unicesumar.foodhub.converter.BooleanToStringConverter;
import br.edu.unicesumar.foodhub.domain.embedded.Coordenadas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco implements BaseEntity {

	private static final String MENSAGEM_CEP = "Tamanho do CEP invalido.";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ENDERECO")
	@SequenceGenerator(name = "S_ENDERECO", sequenceName = "S_ENDERECO", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "numero", nullable = false)
	private String numero;

	@Column(name = "complemento")
	private String complemento;

	@Length(min = 8, max = 8, message = MENSAGEM_CEP)
	@NotEmpty
	@Column(name = "cep", nullable = false)
	private String cep;

	@NotEmpty
	@Column(name = "lougradouro", nullable = false)
	private String lougradouro;

	@NotEmpty
	@Column(name = "bairro", nullable = false)
	private String bairro;

	@ManyToOne(cascade = CascadeType.DETACH, optional = false)
	@JoinColumn(name = "id_cidade", nullable = false)
	private Cidade cidade;

	@Embedded
	private Coordenadas coordenadas;

	@Column(name = "principal")
	@Convert(converter = BooleanToStringConverter.class)
	private Boolean principal;

	@Column(name = "apelido")
	private String apelido;

}
