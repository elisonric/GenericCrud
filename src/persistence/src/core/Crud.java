package persistence.src.core;

import core.annotation.Column;
import core.annotation.Id;
import core.annotation.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Crud {

	private Class entityClass;

	public Crud(Class entityClass) {
		this.entityClass = entityClass;
	}

	private String getTable() {
		String table = "";
		if(entityClass.isAnnotationPresent(Table.class)) {
			table = ((Table) entityClass.getAnnotation(Table.class)).value();
		}
		return table;
	}

	private List<String> getFields() {
		List<String> result = new ArrayList<>();
		for(Field field : entityClass.getDeclaredFields()) {
			if(field.isAnnotationPresent(Column.class)) {
				String column = field.getAnnotation(Column.class).value();
				result.add(column);
			}
		}
		return result;
	}

	private String getFieldsWithComma() {
		String fields = "";
		String comma = "";
		for(String field : getFields()) {
			fields += comma + field;
			comma = ",";
		}
		return fields;
	}

	private String getIdField() {
		String id = "";
		for(Field field : entityClass.getDeclaredFields()) {
			if(field.isAnnotationPresent(Id.class)) {
				id = field.getAnnotation(Column.class).value();
			}
		}
		return id;
	}

	private Class getIdFieldType() {
		for(Field field : entityClass.getDeclaredFields()) {
			if(field.isAnnotationPresent(Id.class)) {
				return field.getType();
			}
		}
		return null;
	}

	private String getStringValueByType(String value, Class type) {
		if(type == String.class) {
			return "'" + value + "'";
		}
		return value;
	}

	public String select(String id) {
		String sqlTemplate = "SELECT %s FROM %s WHERE %s = %s";
		return String.format(sqlTemplate, getFieldsWithComma(), getTable(), getIdField(), getStringValueByType(id, getIdFieldType()));
	}

	public String insert(Object entity) throws NoSuchFieldException, IllegalAccessException {
		String sqlTemplate = "INSERT INTO %s VALUES (%s)";
		String values = "";
		String comma = "";
		List<String> fields = getFields();
		for(String fieldStr : fields) {
			Field field = entityClass.getDeclaredField(fieldStr);
			field.setAccessible(true);
			Object value = field.get(entity);
			values += comma + getStringValueByType(value.toString(), field.getType());
			comma = ",";
		}
		return String.format(sqlTemplate, getTable(), values);
	}
}
