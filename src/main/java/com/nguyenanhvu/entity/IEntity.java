package com.nguyenanhvu.entity;

public interface IEntity<IDCLASS extends Comparable<IDCLASS>> {

	public IDCLASS getId();
	
	public void setId(IDCLASS id);
	
	public boolean isDeleted();
	
	public void setDeleted();
	
	public void setDeleted(boolean deleted);
}
