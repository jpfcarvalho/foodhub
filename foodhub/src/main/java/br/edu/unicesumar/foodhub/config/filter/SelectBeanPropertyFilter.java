package br.edu.unicesumar.foodhub.config.filter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import lombok.SneakyThrows;
import lombok.ToString;

public class SelectBeanPropertyFilter extends SimpleBeanPropertyFilter {

	private static final String GET_PREFIX = "get";

	public static final String FILTER_NAME = "FILTER-FIELDS";

	private final List<String> fields = new ArrayList<>();

	private static final CharSequence NOT_CHAR = "!";

	private final transient Logger log = LoggerFactory.getLogger(getClass());

	private final Stack<StackItem> stackPath = new Stack<>();

	private Object lastPojo;

	private String lastPropertyName;

	@ToString
	private class StackItem {

		private final String path;
		private final Object pojo;

		StackItem(String path, Object pojo) {
			super();
			this.path = path;
			this.pojo = pojo;
		}

	}

	@Override
	protected boolean include(BeanPropertyWriter writer) {

		return true;

	}

	@Override
	protected boolean include(PropertyWriter writer) {

		lastPropertyName = writer.getName();

		String propertyName = getPathProperty(writer.getName());

		final StringBuilder innerPath = new StringBuilder();

		if (!stackPath.isEmpty()) {
			innerPath.append(stackPath.peek().path);
			innerPath.append('.');
		}

		innerPath.append(writer.getName().concat("."));

		boolean addField = fields.contains(propertyName) || existsOnFields(innerPath.toString());

		if (!addField) {

			addField = existsNotField() && !fields.contains(NOT_CHAR + propertyName);

		}

		log.trace(String.format("Filter property %s result %s, compare to %s, inner property %s", propertyName,
				addField, fields, innerPath.toString()));

		return addField;

	}

	private boolean existsOnFields(final String path) {

		return fields.stream().anyMatch(field -> field.startsWith(path))
				|| fields.stream().anyMatch(field -> field.contains(NOT_CHAR) && field.startsWith(NOT_CHAR + path));
	}

	private boolean existsNotField() {
		return fields.stream().anyMatch(field -> field.contains(NOT_CHAR));
	}

	@Override
	@SneakyThrows
	public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) {

		log.trace("serializeAsField " + writer.getName());

		if (pojo != lastPojo) {

			if (lastPojo != null) {

				String candidateProperty = getPathProperty(lastPropertyName).concat(".");

				boolean existsOnFields = existsOnFields(candidateProperty);

				log.trace("candidate field " + candidateProperty + " exists: " + existsOnFields);

				Object returnValue = getValueFromProperty(existsOnFields);

				if (existsOnFields && validateIsSame(pojo, returnValue)) {

					pushPath(pojo, returnValue);

				} else {

					popPath(pojo);

				}

			}

			lastPojo = pojo;

		}

		super.serializeAsField(pojo, jgen, provider, writer);
	}

	private void popPath(Object pojo) {

		while (!stackPath.isEmpty() && !stackPath.peek().pojo.equals(pojo)) {

			internalPop();

		}

	}

	private void internalPop() {

		log.trace("Pop " + stackPath.peek().path);
		stackPath.pop();

	}

	private void pushPath(Object pojo, Object returnValue) {

		String newPath = getPathProperty(lastPropertyName);

		if (isCollection(returnValue)) {

			List<Object> list = Arrays.asList(((Collection<?>) returnValue).toArray());

			for (int i = list.size() - 1; i >= 0; i--) {

				internalPush(list.get(i), newPath);
			}

		} else {

			internalPush(pojo, newPath);

		}

	}

	private void internalPush(Object pojo, String newPath) {
		stackPath.push(new StackItem(newPath, pojo));
		log.trace("Push " + newPath);
	}

	@SneakyThrows
	private Object getValueFromProperty(boolean existsOnFields) {

		if (!existsOnFields) {
			return null;
		}

		log.trace("accessing property " + lastPropertyName);

		Method method = findGetMethod();

		if (method == null) {

			throw new IllegalStateException(String.format("Não encontrado o get para a property %s para o objeto %s.",
					lastPropertyName, lastPojo));
		}

		return method.invoke(lastPojo);
	}

	private Method findGetMethod() {

		Method method = findMethodByFieldName(lastPropertyName);

		if (method == null) {

			List<Field> fields = Arrays.asList(lastPojo.getClass().getDeclaredFields());

			for (Field field : fields) {

				if (field.isAnnotationPresent(JsonProperty.class)) {

					JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);

					if (lastPropertyName.equals(jsonProperty.value())) {

						return findMethodByFieldName(field.getName());
					}

				}

			}
		}

		return method;
	}

	private Method findMethodByFieldName(String fieldName) {

		final String methodGetName = GET_PREFIX + StringUtils.capitalize(fieldName);

		return findMethod(lastPojo.getClass(), methodGetName);
	}

	private boolean validateIsSame(Object pojo, Object returnValue) {

		if (isCollection(returnValue)) {

			return ((Collection<?>) returnValue).contains(pojo);
		}

		return pojo.equals(returnValue);
	}

	private String getPathProperty(String property) {

		if (stackPath.isEmpty()) {
			return property;
		}

		return stackPath.peek().path.concat(".").concat(property);

	}

	public SelectBeanPropertyFilter addProperty(String property) {
		fields.add(property);
		return this;
	}

	public List<String> getFields() {
		return fields;
	}

	private boolean isCollection(Object obj) {

		if (obj == null) {
			return false;
		}

		return obj instanceof Collection<?>;
	}

	private Method findMethod(Class<?> clazz, String methodName) {

		return MethodUtils.getAccessibleMethod(clazz, methodName);
	}

}
