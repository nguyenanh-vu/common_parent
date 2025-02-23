package com.nguyenanhvu.dao;

import java.util.Collection;

import com.nguyenanhvu.entity.INamedEntity;

public interface INamedEntityDao<IDCLASS extends Comparable<IDCLASS>, T extends INamedEntity<IDCLASS>> extends IDao<IDCLASS, T> {

	public T findByName(String name);
	
	public Collection<T> findByNames(Collection<String> names);
	
	public boolean removeByName(String name);
	
	public int removeByNames(Collection<String> names);
	
	public boolean deleteByName(String name);
	
	public int deleteByNames(Collection<String> names);
	
	public boolean restoreByName(String name);
	
	public int restoreByNames(Collection<String> names);
	
}
