package com.nguyenanhvu.entity;

public interface IEntity<IDCLASS extends Comparable<IDCLASS>> {

	public IDCLASS getId();
	
	public IEntity<IDCLASS> setId(IDCLASS id);
	
	public boolean isDeleted();
	
	public IEntity<IDCLASS> setDeleted();
	
	public IEntity<IDCLASS> setDeleted(boolean deleted);
}
