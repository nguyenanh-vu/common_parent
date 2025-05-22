package com.nguyenanhvu.collections.tuplemap;

import java.util.Map;

import com.nguyenanhvu.collections.tuple.Quad;

public class QuadMap<A, B, C, D, T> extends TupleMap<Quad<A, B, C, D>, T> {
	
	public QuadMap() {
		super();
	}
	
	public QuadMap(Map<Quad<A, B, C, D>, T> map) {
		super(map);
	}
	
	public boolean containsKey(A a, B b, C c, D d) {
		return map.containsKey(getKey(a, b, c, d));
	}

	public T get(A a, B b, C c, D d) {
		return map.get(getKey(a, b, c, d));
	}
	
	public T getWithDefault(A a, B b, C c, D d, T def) {
		T res = map.get(getKey(a, b, c, d));
		return res == null ? def : res;
	}

	public T put(A a, B b, C c, D d, T value) {
		return map.put(getKey(a, b, c, d), value);
	}
	
	private Quad<A, B, C, D> getKey(A a, B b, C c, D d) {
		return new Quad<A, B, C, D>(a, b, c, d);
	}
}
