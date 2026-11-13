package com.nguyenanhvu.serialisation.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.nguyenanhvu.serialisation.DataModel;
import com.nguyenanhvu.serialisation.Factory;
import com.nguyenanhvu.serialisation.Field;

public abstract class AbstractFactory<T extends DataModel<T>> implements Factory<T> {
	
	protected final Map<String, Field<?,T>> fieldMap;
	
	public AbstractFactory(Set<Field<?,T>> fields) {
		fieldMap = new HashMap<>();
		for (Field<?,T> f : fields) {
			fieldMap.put(f.getName().toUpperCase(), f);
		}
	}
	
	@Override
	public Set<Field<?,T>> getFields() {
		return new HashSet<>(fieldMap.values());
	}

	@Override
	public Map<String, Field<?,T>> getFieldAsMap() {
		return Collections.unmodifiableMap(fieldMap);
	}

	@Override
	public Field<?,T> getField(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}
		return fieldMap.getOrDefault(name.toUpperCase(), null);
	}

}
