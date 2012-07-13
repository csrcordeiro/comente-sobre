package br.com.comentesobre.daos;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Query;

public interface Dao<T> {
    public T search(Serializable id);
	public Query makeQuery(String query);
    public void persist(T entity);
    public void delete(T entity);
    public void update(T entity);
    public Collection<T> listAll();
    public void resfresh(T entity);
}
