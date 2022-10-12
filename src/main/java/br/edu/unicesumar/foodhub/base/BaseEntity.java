package br.edu.unicesumar.foodhub.base;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.unicesumar.foodhub.config.filter.SelectBeanPropertyFilter;

@JsonFilter(SelectBeanPropertyFilter.FILTER_NAME)
public interface BaseEntity {

	Long getId();

	void setId(Long id);

	@JsonIgnore
	default boolean isNew() {
		return getId() == null;
	}
}
