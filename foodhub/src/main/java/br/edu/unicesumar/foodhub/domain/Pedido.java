package br.edu.unicesumar.foodhub.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import br.edu.unicesumar.foodhub.config.filter.JsonFilterFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pedido")
@JsonFilterFields(of = { "id", "precoTotal", "dataHora", "statusPedido.status", "pagamento.id", "pessoa.id",
		"pessoa.nome", "pessoa.sobrenome", "produtos.id", "produtos.precoProduto", "produtos.produto.id",
		"produtos.produto.nome", "produtos.complementos.id", "produtos.complementos.valorComplemento",
		"produtos.complementos.quantidade", "produtos.complementos.produtoComplemento.id",
		"produtos.complementos.produtoComplemento.nome", "endereco.id", "endereco.numero", "endereco.complemento",
		"endereco.cep", "endereco.logradouro", "endereco.bairro", "endereco.cidade.nome", "restauranteId",
		"restauranteNomeFantasia" })
public class Pedido implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PEDIDO")
	@SequenceGenerator(name = "S_PEDIDO", sequenceName = "S_PEDIDO", allocationSize = 1)
	private Long id;

	@NotNull
	@Column(name = "preco_total", nullable = false)
	private BigDecimal precoTotal;

	@NotNull
	@Column(name = "data_hora", nullable = false)
	private LocalDateTime dataHora = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_status_pedido", nullable = false)
	private StatusPedido statusPedido = StatusPedido.of(1L);

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pagamento")
	private Pagamento pagamento;

	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa", nullable = false)
	private Pessoa pessoa;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JsonBackReference("restaurante_pedido")
	@JsonIgnoreProperties({ "pedidos" })
	@JoinColumn(name = "id_restaurante", nullable = false)
	private Restaurante restaurante;

	@NotNull
	@Size(min = 1)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_pedido", nullable = false)
	private List<PedidoProduto> produtos = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_comentario")
	private Comentario comentario;

	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_endereco", nullable = false)
	private Endereco endereco;

	public Long getRestauranteId() {
		return Optional.ofNullable(restaurante).orElse(new Restaurante()).getId();
	}

	public String getRestauranteNomeFantasia() {
		return Optional.ofNullable(restaurante).orElse(new Restaurante()).getNomeFantasia();
	}

}
