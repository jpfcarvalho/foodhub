package br.edu.unicesumar.foodhub.config.filter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class FilterJsonResponseAdvice extends AbstractMappingJacksonResponseBodyAdvice {

	private static final String NOT_FIELD = "!";
	private static final String DELIMITER = ",";
	public static final String PARAM_NAME = "fields";
	private static final String ALL_VALUE = "all";

	@Override
	protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
			MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {

		HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();

		createFilter(bodyContainer, servletRequest);
	}

	public void createFilter(MappingJacksonValue bodyContainer, HttpServletRequest servletRequest) {
		String fieldsParameter = servletRequest.getParameter(PARAM_NAME);

		JsonFilterFields jsonFilterFields = extractJsonFilterFieldsAnnotation(bodyContainer.getValue());

		if (jsonFilterFields != null) {

			fieldsParameter = mergeFields(fieldsParameter, Arrays.asList(jsonFilterFields.of()));

		}

		createFilterFields(bodyContainer, fieldsParameter);
	}

	private JsonFilterFields extractJsonFilterFieldsAnnotation(Object value) {

		if (value == null) {

			return null;
		}

		if (value.getClass().isAnnotationPresent(JsonFilterFields.class)) {

			return value.getClass().getAnnotation(JsonFilterFields.class);

		} else if (isCollection(value)) {

			List<Object> list = Arrays.asList(((Collection<?>) value).toArray());

			if (!list.isEmpty()) {

				return extractJsonFilterFieldsAnnotation(list.get(0));
			}

		} else if (value instanceof Page<?>) {

			return extractJsonFilterFieldsAnnotation(((Page<?>) value).getContent());

		}

		return null;
	}

	private String mergeFields(String fieldsParameter, List<String> fieldsDefault) {

		if (fieldsParameter != null) {

			if (fieldsParameter.equals(ALL_VALUE)) {
				return null;
			}

			List<String> fields = Arrays.asList(fieldsParameter.split(DELIMITER));

			if (fields.stream().anyMatch(field -> field.startsWith(NOT_FIELD))) {

				return String.join(DELIMITER, fieldsDefault.stream()
						.filter(field -> !fields.contains(NOT_FIELD + field)).collect(Collectors.toList()));

			}

			return String.join(DELIMITER,
					fields.stream().filter(field -> fieldsDefault.contains(field)).collect(Collectors.toList()));
		}

		return String.join(DELIMITER, fieldsDefault);
	}

	public void createFilterFields(MappingJacksonValue bodyContainer, String fieldsParameter) {
		final SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		filterProvider.setDefaultFilter(SimpleBeanPropertyFilter.serializeAllExcept("blah"));

		bodyContainer.setFilters(filterProvider);

		if (fieldsParameter != null) {
			final SelectBeanPropertyFilter selectBeanPropertyFilter = new SelectBeanPropertyFilter();
			selectBeanPropertyFilter.getFields().addAll(Arrays.asList(fieldsParameter.split(DELIMITER)));

			filterProvider.addFilter(SelectBeanPropertyFilter.FILTER_NAME, selectBeanPropertyFilter);
		}
	}

	private boolean isCollection(Object obj) {

		if (obj == null) {
			return false;
		}

		return obj instanceof Collection<?>;
	}

}
