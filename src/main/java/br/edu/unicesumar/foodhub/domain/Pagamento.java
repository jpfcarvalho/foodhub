package br.edu.unicesumar.foodhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@Table(name = "pagamento")
@JsonFilterFields(of = { "id", "token", "status", "pessoa.id" })
public class Pagamento implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PAGAMENTO")
	@SequenceGenerator(name = "S_PAGAMENTO", sequenceName = "S_PAGAMENTO", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "token", nullable = false)
	private String token;

	@NotEmpty
	@Column(name = "status", nullable = false)
	private String status;

	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JsonBackReference("pessoa_pagamento")
	@JsonIgnoreProperties({ "pagamentos" })
	@JoinColumn(name = "id_pessoa", nullable = false)
	private Pessoa pessoa;
}
