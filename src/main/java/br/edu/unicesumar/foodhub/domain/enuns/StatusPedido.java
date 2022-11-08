package br.edu.unicesumar.foodhub.domain.enuns;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum StatusPedido {

	ABERTO(1L, "Aberto"), EM_PREPARO(2L, "Em preparo"), SAIU_PARA_ENTREGA(3L, "Saiu para entrega"),
	FINALIZADO(4L, "Finalizado"), RECUSADO(5L, "Recusado");

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
			return EM_PREPARO;
		case 3:
			return SAIU_PARA_ENTREGA;
		case 4:
			return FINALIZADO;
		case 5:
			return RECUSADO;
		default:
			return null;
		}
	}

}
