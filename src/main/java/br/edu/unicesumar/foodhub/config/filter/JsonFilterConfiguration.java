package br.edu.unicesumar.foodhub.config.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;

public class JsonFilterConfiguration {

	@Bean
	public FilterJsonResponseAdvice filterJsonResponseAdvice() {
		return new ControllerFilterJsonResponseAdvice();
	}

	@ControllerAdvice
	public static class ControllerFilterJsonResponseAdvice extends FilterJsonResponseAdvice {

	}
}
