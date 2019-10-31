package daos.jpa;

import daos.GenericDAO;
import jpa.JPAUtil;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class GenericJPADAO<T> implements GenericDAO<T> {

    private Class<T> persistenceClass;

    public GenericJPADAO(Class<T> persistenceClass) {
        this.persistenceClass = persistenceClass;
    }

    public void save(T entity) {
        JPAUtil.getEntityManager().merge(entity);
    }

    public void delete(T entity) {
        JPAUtil.getEntityManager().remove(JPAUtil.getEntityManager().merge(entity));
    }

    public List<T> findAll() {
        CriteriaQuery<T> cq = JPAUtil.getEntityManager().getCriteriaBuilder().createQuery(persistenceClass);
        cq.from(persistenceClass);
        return JPAUtil.getEntityManager().createQuery(cq).getResultList();
    }

    public void beginTransaction() {
        JPAUtil.beginTransaction();
    }

    public void commit() {
        JPAUtil.commit();
    }

    public void rollback() {
        JPAUtil.rollback();
    }

    public void close() {
        JPAUtil.closeEntityManager();
    }
}
