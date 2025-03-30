package com.nguyenanhvu.entity;

public interface INamedEntity<IDCLASS extends Comparable<IDCLASS>> extends IEntity<IDCLASS> {
	
	public String getName();
	
	public IEntity<IDCLASS> setName(String name);
}
