package com.nguyenanhvu.entity.impl;

import com.nguyenanhvu.entity.INamedEntity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class AbstractNamedEntity<IDCLASS extends Comparable<IDCLASS>> 
extends AbstractEntity<IDCLASS> 
implements INamedEntity<IDCLASS> {

	@Column(unique = true, name="NAME", nullable=false)
	@Getter
	@Setter
	private String name = "";
	
	public AbstractNamedEntity() {
		super();
	}
	
	public AbstractNamedEntity(IDCLASS id) {
		super(id);
	}
	
	public AbstractNamedEntity(boolean deleted) {
		super(deleted);
	}
	
	public AbstractNamedEntity(IDCLASS id, boolean deleted) {
		super(id, deleted);
	}
	
	public AbstractNamedEntity(String name) {
		super();
		this.name = name;
	}
	
	public AbstractNamedEntity(IDCLASS id, String name) {
		super(id);
		this.name = name;
	}
	
	public AbstractNamedEntity(boolean deleted, String name) {
		super(deleted);
		this.name = name;
	}
	
	public AbstractNamedEntity(IDCLASS id, boolean deleted, String name) {
		super(id, deleted);
		this.name = name;
	}
}
