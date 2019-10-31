package daos.jpa;

import daos.GenericDAO;

import java.util.List;

public class GenericJPADAO<T> implements GenericDAO<T> {

    private Class<T> persistenceClass;

    public GenericJPADAO(Class<T> persistenceClass) {
        this.persistenceClass = persistenceClass;
    }

    public void save(T entity) {

    }

    public void delete(T entity) {

    }

    public List<T> findAll() {
        return null;
    }

    public void beginTransaction() {

    }

    public void commit() {

    }

    public void rollback() {

    }

    public void close() {

    }
}
