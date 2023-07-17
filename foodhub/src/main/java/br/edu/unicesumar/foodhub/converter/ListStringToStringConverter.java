package br.edu.unicesumar.foodhub.converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ListStringToStringConverter implements AttributeConverter<List<String>, String> {

	private static final String VIRGULA = ",";

	@Override
	public String convertToDatabaseColumn(List<String> attribute) {

		return attribute.stream().collect(Collectors.joining(VIRGULA));

	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {

		return Arrays.asList(dbData.split(VIRGULA));
	}
}
