package com.nguyenanhvu.entity.impl;

import com.nguyenanhvu.entity.INamedEntity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@MappedSuperclass
@Accessors(chain = true)
public abstract class AbstractNamedEntity<IDCLASS extends Comparable<IDCLASS>> 
extends AbstractEntity<IDCLASS> 
implements INamedEntity<IDCLASS> {

	@Column(unique = true, name="NAME", nullable=false)
	@Getter
	@Setter
	private String name = "";
	
	protected AbstractNamedEntity() {
		super();
	}
	
	protected AbstractNamedEntity(IDCLASS id) {
		super(id);
	}
	
	protected AbstractNamedEntity(boolean deleted) {
		super(deleted);
	}
	
	protected AbstractNamedEntity(IDCLASS id, boolean deleted) {
		super(id, deleted);
	}
	
	protected AbstractNamedEntity(String name) {
		super();
		this.name = name;
	}
	
	protected AbstractNamedEntity(IDCLASS id, String name) {
		super(id);
		this.name = name;
	}
	
	protected AbstractNamedEntity(boolean deleted, String name) {
		super(deleted);
		this.name = name;
	}
	
	protected AbstractNamedEntity(IDCLASS id, boolean deleted, String name) {
		super(id, deleted);
		this.name = name;
	}
}
