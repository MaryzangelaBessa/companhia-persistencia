package daos;

import java.util.List;

public interface GenericDAO<T> {

    void save(T entity);

    void delete(T entity);

    List<T> findAll();

    void beginTransaction();

    void commit();

    void rollback();

    void close();
}
