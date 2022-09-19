package br.edu.unicesumar.foodhub.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pedido")
public class Pedido implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PEDIDO")
	@SequenceGenerator(name = "S_PEDIDO", sequenceName = "S_PEDIDO", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "preco_total", nullable = false)
	private BigDecimal precoTotal;

	@NotEmpty
	@Column(name = "data_hora", nullable = false)
	private LocalDateTime dataHora;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_status_pedido", nullable = false, insertable = false, updatable = false)
	private StatusPedido statusPedido;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pagamento")
	private Pagamento pagamento;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_pessoa", nullable = false, insertable = false, updatable = false)
	private Pessoa pessoa;

	@ManyToOne(optional = false)
	@JsonBackReference
	@JsonIgnoreProperties({ "pedidos" })
	@JoinColumn(name = "id_restaurante", nullable = false, insertable = false, updatable = false)
	private Restaurante restaurante;

	@Size(min = 1)
	@NotNull
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_pedido", nullable = false)
	private List<PedidoProduto> produtos = new ArrayList<>();

}
