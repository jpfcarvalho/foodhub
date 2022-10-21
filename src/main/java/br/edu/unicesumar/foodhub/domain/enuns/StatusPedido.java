package br.edu.unicesumar.foodhub.domain.enuns;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum StatusPedido {

	ABERTO(1L, "Aberto"), PREPARANDO(2L, "Preparando"), ENTREGA(3L, "Entrega"), FINALIZADO(4L, "Finalizado");

	private Long value;
	private String descricao;

	public Long getValue() {
		return value;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusPedido from(Long value) {
		switch (value.intValue()) {
		case 1:
			return ABERTO;
		case 2:
			return PREPARANDO;
		case 3:
			return ENTREGA;
		case 4:
			return FINALIZADO;
		default:
			return null;
		}
	}

}
