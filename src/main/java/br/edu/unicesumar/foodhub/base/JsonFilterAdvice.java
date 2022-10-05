package br.edu.unicesumar.foodhub.base;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import io.swagger.v3.oas.annotations.Operation;

@ControllerAdvice
public class JsonFilterAdvice implements ResponseBodyAdvice<Object> {

	private static String FIELDS = "fields";
	private static String VIRGULA = ",";

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return Arrays.asList(returnType.getMethodAnnotations()).stream()
				.filter(f -> f.annotationType().equals(Operation.class)).findAny().isEmpty();

	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType,
			org.springframework.http.server.ServerHttpRequest request, ServerHttpResponse response) {
		String fields = ((ServletServerHttpRequest) request).getServletRequest().getParameter(FIELDS);
		return new FilterMappingJacksonValue<>(body,
				StringUtils.isEmpty(fields) ? new String[] {} : fields.split(VIRGULA));
	}
}
