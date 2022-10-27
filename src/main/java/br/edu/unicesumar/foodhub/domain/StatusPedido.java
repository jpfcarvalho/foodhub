package br.edu.unicesumar.foodhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import br.edu.unicesumar.foodhub.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "status_pedido")
public class StatusPedido implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_STATUS_PEDIDO")
	@SequenceGenerator(name = "S_STATUS_PEDIDO", sequenceName = "S_STATUS_PEDIDO", allocationSize = 1)
	private Long id;

	@NotEmpty
	@Column(name = "status", nullable = false)
	private String status;

	public static StatusPedido of(Long id) {
		StatusPedido statusPedido = new StatusPedido();
		statusPedido.setId(id);
		return statusPedido;
	}

}
