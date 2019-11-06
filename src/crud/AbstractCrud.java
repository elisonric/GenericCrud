package crud;

import annotations.Column;
import connector.Connector;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbstractCrud <T>  implements CrudRepository<T>{

    private GenericCrud<T> genericCrud;
    private static Connection connection;
    private Class<T> entity;

    public AbstractCrud(Class<T> clazz) {
        AbstractCrud.connection = Connector.getConexao();
        this.entity = clazz;
        genericCrud = new GenericCrud<T>(entity);
    }

    @Override
    public final List<T> findAll() {
        List<Field> fields = new ArrayList<>(genericCrud.getFields());
        String table = genericCrud.getTable();
        String sql;
        sql = String.format("SELECT * FROM %s", table);
        List<T> entityList = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
	  		ResultSet resultSet = preparedStatement.executeQuery();
	  		while (resultSet.next()) {
                T entity = this.entity.newInstance();
	  		    for (Field field : fields) {
                        field.setAccessible(true);
                        field.set(entity, resultSet.getObject(field.getAnnotation(Column.class).value()));
                }
                entityList.add(entity);
            }
	  	} catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return entityList;
    }

    @Override
    public T findById(Long id) {
        return null;
    }

    @Override
    public T save(T t) {
        return null;
    }

    @Override
    public void deleteById() {

    }
}
