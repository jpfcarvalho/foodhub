package br.edu.unicesumar.foodhub.base;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.apache.commons.lang3.BooleanUtils;

@Converter(autoApply = true)
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {

	private static final String SIM = "S";
	private static final String NAO = "N";

	@Override
	public String convertToDatabaseColumn(Boolean attribute) {

		if (BooleanUtils.isTrue(attribute)) {
			return SIM;
		}

		return NAO;

	}

	@Override
	public Boolean convertToEntityAttribute(String dbData) {

		if (dbData == null) {
			return Boolean.FALSE;
		}

		return SIM.equalsIgnoreCase(dbData);
	}
}
