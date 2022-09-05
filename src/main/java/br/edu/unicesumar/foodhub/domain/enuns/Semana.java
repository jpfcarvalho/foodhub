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
}
