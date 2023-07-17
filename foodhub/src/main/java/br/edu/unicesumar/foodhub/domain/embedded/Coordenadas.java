package br.edu.unicesumar.foodhub.domain.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Coordenadas {

	@NotNull
	@Column(name = "latitude", nullable = false)
	private Long latitude;

	@NotNull
	@Column(name = "longitude", nullable = false)
	private Long longitude;

}
