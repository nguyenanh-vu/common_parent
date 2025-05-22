package com.nguyenanhvu.collections.tuplemap;

import java.util.Map;

import com.nguyenanhvu.collections.tuple.Trio;

public class TrioMap<A, B, C, T> extends TupleMap<Trio<A, B, C>, T> {
	
	public TrioMap() {
		super();
	}
	
	public TrioMap(Map<Trio<A, B, C>, T> map) {
		super(map);
	}
	
	public boolean containsKey(A a, B b, C c) {
		return map.containsKey(getKey(a, b, c));
	}

	public T get(A a, B b, C c) {
		return map.get(getKey(a, b, c));
	}
	
	public T getWithDefault(A a, B b, C c, T def) {
		T res = map.get(getKey(a, b, c));
		return res == null ? def : res;
	}

	public T put(A a, B b, C c, T value) {
		return map.put(getKey(a, b, c), value);
	}
	
	private Trio<A, B, C> getKey(A a, B b, C c) {
		return new Trio<A, B, C>(a, b, c);
	}
}
