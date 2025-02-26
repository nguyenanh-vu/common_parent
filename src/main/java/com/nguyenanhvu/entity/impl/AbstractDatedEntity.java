package com.nguyenanhvu.entity.impl;

import com.nguyenanhvu.entity.IDatedEntity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class AbstractDatedEntity<IDCLASS extends Comparable<IDCLASS>>
extends AbstractEntity<IDCLASS> 
implements IDatedEntity<IDCLASS> {

	@Column(name = "TIMESTAMP")
	@Getter
	@Setter
	private java.sql.Timestamp timestamp = null;
	
	public AbstractDatedEntity() {
		super();
	}
	
	public AbstractDatedEntity(IDCLASS id) {
		super(id);
	}
	
	public AbstractDatedEntity(boolean deleted) {
		super(deleted);
	}
	
	public AbstractDatedEntity(IDCLASS id, boolean deleted) {
		super(id, deleted);
	}
	
	public AbstractDatedEntity(java.sql.Timestamp timestamp) {
		super();
		this.timestamp = timestamp;
	}
	
	public AbstractDatedEntity(IDCLASS id, java.sql.Timestamp timestamp) {
		super(id);
		this.timestamp = timestamp;
	}
	
	public AbstractDatedEntity(boolean deleted, java.sql.Timestamp timestamp) {
		super(deleted);
		this.timestamp = timestamp;
	}
	
	public AbstractDatedEntity(IDCLASS id, boolean deleted, java.sql.Timestamp timestamp) {
		super(id, deleted);
		this.timestamp = timestamp;
	}
	
	public void touch() {
		this.timestamp = new java.sql.Timestamp(System.currentTimeMillis());
	}
}
