package com.nguyenanhvu.entity.impl;

import com.nguyenanhvu.entity.IEntity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.experimental.Accessors;

@MappedSuperclass
@Accessors(chain = true)
public abstract class AbstractEntity<IDCLASS extends Comparable<IDCLASS>> 
implements IEntity<IDCLASS> {
	
	@Column(name="DELETED")
	private int deleted = 0;
	
	protected AbstractEntity() {
	}
	
	protected AbstractEntity(IDCLASS id) {
		setId(id);
	}
	
	protected AbstractEntity(boolean deleted) {
		this.deleted = deleted ? 1 : 0;
	}
	
	protected AbstractEntity(IDCLASS id, boolean deleted) {
		setId(id);
		this.deleted = deleted ? 1 : 0;
	}
	
	public boolean isDeleted() {
		return deleted != 0;
	}
	
	public IEntity<IDCLASS> setDeleted() {
		return setDeleted(true);
	}
	
	public IEntity<IDCLASS> setDeleted(boolean deleted) {
		this.deleted  = deleted ? 1 : 0;
		return this;
	}
	
}
