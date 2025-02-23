package com.nguyenanhvu.dao;

import java.util.Collection;
import java.util.Map;

import com.nguyenanhvu.entity.IEntity;

import jakarta.persistence.EntityManager;

public interface IDao<IDCLASS extends Comparable<IDCLASS>, T extends IEntity<IDCLASS>> {
	
	public boolean save(T entity);
	
	public int save(Collection<T> entities);
	
	public boolean update(T entity);
	
	public int update(Collection<T> entities);
	
	public boolean remove(T entity);
	
	public int remove(Collection<T> entities);
	
	public boolean removeById(IDCLASS id);
	
	public int removeByIds(Collection<IDCLASS> ids);
	
	public boolean delete(T entity);
	
	public int delete(Collection<T> entities);
	
	public boolean deleteById(IDCLASS id);
	
	public int deleteByIds(Collection<IDCLASS> ids);
	
	public boolean restore(T entity);
	
	public int restore(Collection<T> entities);
	
	public boolean restoreById(IDCLASS id);
	
	public int restoreByIds(Collection<IDCLASS> ids);
	
	public T findById(IDCLASS id);
	
	public Collection<T> findByIds(Collection<IDCLASS> ids);
	
	public Collection<T> findAll();
	
	public Collection<T> findAll(boolean deleted);
	
	public EntityManager getEntityManager();
	
	public void handleException(Exception e);
	
	public boolean testConnection();
	
	public ISearchProperty<T>[] getSearchProperties();
	
	public Collection<T> find(Map<ISearchProperty<T>, Object> properties, 
			Map<ISearchProperty<T>, Boolean> orderBy, Boolean deleted);
	
}
