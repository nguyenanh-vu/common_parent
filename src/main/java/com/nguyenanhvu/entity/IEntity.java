package com.nguyenanhvu.entity;

public interface IEntity<IDCLASS extends Comparable<IDCLASS>> {

	public IDCLASS getId();
	
	public boolean isDeleted();
	
	public void setDeleted();
	
	public void setDeleted(boolean deleted);
}
