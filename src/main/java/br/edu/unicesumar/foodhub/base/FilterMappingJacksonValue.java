package br.edu.unicesumar.foodhub.base;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class FilterMappingJacksonValue<T> extends MappingJacksonValue {

	private static String FILTER_FIELDS = "filterFields";

	public FilterMappingJacksonValue(final T value, final String... filters) {
		super(value);
		setFilters(new SimpleFilterProvider().addFilter(FILTER_FIELDS,
				filters.length > 0 ? SimpleBeanPropertyFilter.filterOutAllExcept(filters)
						: SimpleBeanPropertyFilter.serializeAll()));
	}
}