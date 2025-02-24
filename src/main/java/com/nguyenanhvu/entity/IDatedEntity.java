package com.nguyenanhvu.entity;

public interface IDatedEntity<IDCLASS extends Comparable<IDCLASS>> extends IEntity<IDCLASS> {
	
	public java.sql.Timestamp getTimestamp();
	
	public void setTimestamp(java.sql.Timestamp timestamp);
}
