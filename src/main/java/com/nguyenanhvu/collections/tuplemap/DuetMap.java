package com.nguyenanhvu.collections.tuplemap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.nguyenanhvu.collections.tuple.Duet;
import com.nguyenanhvu.collections.tuple.Solo;

public class DuetMap<A,B,T> extends TupleMap<Duet<A,B>,T> {
	
	public DuetMap() {
		super();
	}
	
	public DuetMap(Map<Duet<A,B>,T> map) {
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
	
	public Collection<T> getAllA(A a) {
		return getAll(new Solo<A>(a));
	}
	
	public Collection<T> getAllB(B b) {
		return getAll(new Solo<B>(b), 1);
	}
	
	public Collection<Entry<Duet<A,B>,T>> getAllEntriesA(A a) {
		return getAllEntries(new Solo<A>(a));
	}
	
	public Collection<Entry<Duet<A,B>,T>> getAllEntriesB(B b) {
		return getAllEntries(new Solo<B>(b), 1);
	}
	
	public Collection<T> removeAllA(A a) {
		return removeAll(new Solo<A>(a));
	}
	
	public Collection<T> removeAllB(B b) {
		return removeAll(new Solo<B>(b), 1);
	}
	
	public Map<B,T> getSubMapA(A a) {
		Map<B,T> res = new HashMap<>();
		for (Entry<Duet<A,B>,T> entry : getAllEntriesA(a)) {
			res.put(entry.getKey().b, entry.getValue());
		}
		return res;
	}
	
	public Map<A,T> getSubMapB(B b) {
		Map<A,T> res = new HashMap<>();
		for (Entry<Duet<A,B>,T> entry : getAllEntriesB(b)) {
			res.put(entry.getKey().a, entry.getValue());
		}
		return res;
	}
	
	private Duet<A,B> getKey(A a, B b) {
		return new Duet<A,B>(a, b);
	}
}
