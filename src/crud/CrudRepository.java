package crud;

import java.util.List;

public interface CrudRepository<T>{

	public List<T> findAll();

	public T findById(Long id);

	public T save(T t);

	public void deleteById();

}
