package daos;

import java.util.List;

public interface GenericDAO<T> {

    public void insert(T t);

    public void update(T t);

    public void delete(Object id);

    public T find(Object id);

    public List<T> findAll();

    public void close();
}
