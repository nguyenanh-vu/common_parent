package com.nguyenanhvu.entity.impl;

import com.nguyenanhvu.entity.IEntity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class AbstractEntity<IDCLASS extends Comparable<IDCLASS>> 
implements IEntity<IDCLASS> {
	
	@Column(name="DELETED")
	private int deleted = 0;
	
	public AbstractEntity() {
	}
	
	public AbstractEntity(IDCLASS id) {
		setId(id);
	}
	
	public AbstractEntity(boolean deleted) {
		this.deleted = deleted ? 1 : 0;
	}
	
	public AbstractEntity(IDCLASS id, boolean deleted) {
		setId(id);
		this.deleted = deleted ? 1 : 0;
	}
	
	public boolean isDeleted() {
		return deleted != 0;
	}
	
	public void setDeleted() {
		setDeleted(true);
	}
	
	public void setDeleted(boolean deleted) {
		this.deleted  = deleted ? 1 : 0;
	}
	
}
