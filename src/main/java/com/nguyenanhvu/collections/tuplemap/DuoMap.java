package com.nguyenanhvu.collections.tuplemap;

import java.util.Map;

import com.nguyenanhvu.collections.tuple.Duo;

public class DuoMap<A, B, T> extends TupleMap<Duo<A, B>, T> {
	
	public DuoMap() {
		super();
	}
	
	public DuoMap(Map<Duo<A, B>, T> map) {
		super(map);
	}
	
	public boolean containsKey(A a, B b) {
		return map.containsKey(getKey(a, b));
	}

	public T get(A a, B b) {
		return map.get(getKey(a, b));
	}
	
	public T getWithDefault(A a, B b, T def) {
		T res = map.get(getKey(a, b));
		return res == null ? def : res;
	}

	public T put(A a, B b, T value) {
		return map.put(getKey(a, b), value);
	}
	
	private Duo<A, B> getKey(A a, B b) {
		return new Duo<A, B>(a, b);
	}
}
