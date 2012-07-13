package br.com.comentesobre.daos;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JpaDao<T> implements Dao<T>{
    
    private final EntityManager entityManager;
    private final Class clazz;
    
    public JpaDao(EntityManager entityManager, Class clazz){
        this.entityManager = entityManager;
        this.clazz = clazz;
    }

    public T search(Serializable id) {
        return (T) entityManager.find(clazz, id);
    }
    
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public Collection<T> listAll() {
        return (Collection<T>)
                entityManager.
                createQuery("select t from "+ clazz.getName() + " t").
                getResultList();
    }

    public void resfresh(T entity) {
        entityManager.refresh(entity);
    }

    public Query makeQuery(String query) {
        return entityManager.createQuery(query);
    }
}
