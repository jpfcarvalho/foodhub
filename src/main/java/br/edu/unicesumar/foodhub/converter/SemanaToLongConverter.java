package br.edu.unicesumar.foodhub.converter;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.edu.unicesumar.foodhub.domain.enuns.Semana;

@Converter(autoApply = true)
public class SemanaToLongConverter implements AttributeConverter<Semana, Long> {

	@Override
	public Long convertToDatabaseColumn(Semana attribute) {

		if (Objects.nonNull(attribute)) {
			return attribute.getValue();
		}
		return null;
	}

	@Override
	public Semana convertToEntityAttribute(Long dbData) {
		return Semana.from(dbData);
	}
}
