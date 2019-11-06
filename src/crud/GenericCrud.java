package crud;

import annotations.Column;
import annotations.Id;
import annotations.Table;
import connector.Connector;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericCrud<T> {

	private Class entity;

	protected GenericCrud(Class<T> clazz) {
		this.entity = clazz;
	}

	protected final String getTable() {
		String table = "";
		if(entity.isAnnotationPresent(Table.class)) {
			table = ((Table) entity.getAnnotation(Table.class)).value();
		}
		return  table;
	}

	protected final List<Field> getFields() {
		List<Field> fields = new ArrayList<>();
		for (Field field: entity.getDeclaredFields()) {
			if (field.isAnnotationPresent(Column.class)) {
				fields.add(field);
			}
		}
		return fields;
	}

	protected final String getFieldsToSql() {
		List<Field> fields = new ArrayList<>(getFields());
		StringBuilder fieldsToSql = new StringBuilder();
		for (Field field: fields) {
			fieldsToSql.append(field.getAnnotation(Column.class).value());
		}
		fieldsToSql.deleteCharAt(fieldsToSql.length() -1);
		return fieldsToSql.toString();
	}

	protected final List<Field> getIdFields() {
		List<Field> fields = new ArrayList<>();
		for (Field field: entity.getDeclaredFields()) {
			if (field.isAnnotationPresent(Id.class)) {
				fields.add(field);
			}
		}
		return  fields;
	}

}
