package br.edu.unicesumar.foodhub.domain.enuns;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum Semana {

	DOMINGO(1L, "Domingo"), SEGUNDA(2L, "Segunda"), TERCA(3L, "Terça"), QUARTA(4L, "Quarta"), QUINTA(5L, "Quinta"),
	SEXTA(6L, "Sexta"), SABADO(7L, "Sábado");

	private Long value;
	private String descricao;

	public Long getValue() {
		return value;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Semana from(Long value) {
		switch (value.intValue()) {
		case 1:
			return DOMINGO;
		case 2:
			return SEGUNDA;
		case 3:
			return TERCA;
		case 4:
			return QUARTA;
		case 5:
			return QUINTA;
		case 6:
			return SEXTA;
		case 7:
			return SABADO;
		default:
			return null;
		}
	}

}
