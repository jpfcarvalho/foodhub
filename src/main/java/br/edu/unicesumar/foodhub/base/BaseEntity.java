package br.edu.unicesumar.foodhub.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface BaseEntity {

	Long getId();
	
	void setId(Long id);
	
	@JsonIgnore
	default boolean isNew() { return getId() == null;}
}
