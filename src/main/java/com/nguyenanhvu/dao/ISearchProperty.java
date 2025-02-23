package com.nguyenanhvu.dao;

import com.nguyenanhvu.entity.IEntity;

public interface ISearchProperty<T extends IEntity<?>> {
	
	public String getColumnName();
	public Class<?> getClazz();
}
