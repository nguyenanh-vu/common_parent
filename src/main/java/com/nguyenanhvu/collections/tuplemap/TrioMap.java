package com.nguyenanhvu.collections.tuplemap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.nguyenanhvu.collections.tuple.impl.Duet;
import com.nguyenanhvu.collections.tuple.impl.Solo;
import com.nguyenanhvu.collections.tuple.impl.Trio;

public class TrioMap<A,B,C,T> extends TupleMap<Trio<A,B,C>,T> {
	
	public TrioMap() {
		super();
	}
	
	public TrioMap(Map<Trio<A,B,C>, T> map) {
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
	
	public Collection<T> getAllA(A a) {
		return getAll(new Solo<A>(a));
	}
	
	public Collection<T> getAllB(B b) {
		return getAll(new Solo<B>(b), 1);
	}
	
	public Collection<T> getAllC(C c) {
		return getAll(new Solo<C>(c), 2);
	}
	
	public Collection<T> getAllAB(A a, B b) {
		return getAll(new Duet<A,B>(a, b));
	}
	
	public Collection<T> getAllBC(B b, C c) {
		return getAll(new Duet<B,C>(b, c), 1);
	}
	
	public Collection<Entry<Trio<A,B,C>,T>> getAllEntriesA(A a) {
		return getAllEntries(new Solo<A>(a));
	}
	
	public Collection<Entry<Trio<A,B,C>,T>> getAllEntriesB(B b) {
		return getAllEntries(new Solo<B>(b), 1);
	}
	
	public Collection<Entry<Trio<A,B,C>,T>> getAllEntriesC(C c) {
		return getAllEntries(new Solo<C>(c), 2);
	}
	
	public Collection<Entry<Trio<A,B,C>,T>> getAllEntriesAB(A a, B b) {
		return getAllEntries(new Duet<A,B>(a, b));
	}
	
	public Collection<Entry<Trio<A,B,C>,T>> getAllEntriesBC(B b, C c) {
		return getAllEntries(new Duet<B,C>(b, c), 1);
	}
	
	public Collection<T> removeAllA(A a) {
		return removeAll(new Solo<A>(a));
	}
	
	public Collection<T> removeAllB(B b) {
		return removeAll(new Solo<B>(b), 1);
	}
	
	public Collection<T> removeAllC(C c) {
		return removeAll(new Solo<C>(c), 2);
	}
	
	public Collection<T> removeAllAB(A a, B b) {
		return removeAll(new Duet<A,B>(a, b));
	}
	
	public Collection<T> removeAllBC(B b, C c) {
		return removeAll(new Duet<B,C>(b, c), 1);
	}
	
	public DuetMap<B,C,T> getSubMapA(A a) {
		DuetMap<B,C,T> res = new DuetMap<>();
		for (Entry<Trio<A,B,C>,T> entry : getAllEntriesA(a)) {
			res.put(new Duet<B,C>(entry.getKey().b, entry.getKey().c) , entry.getValue());
		}
		return res;
	}
	
	public DuetMap<A,C,T> getSubMapB(B b) {
		DuetMap<A,C,T> res = new DuetMap<>();
		for (Entry<Trio<A,B,C>,T> entry : getAllEntriesB(b)) {
			res.put(new Duet<A,C>(entry.getKey().a, entry.getKey().c) , entry.getValue());
		}
		return res;
	}
	
	public DuetMap<A,B,T> getSubMapC(C c) {
		DuetMap<A,B,T> res = new DuetMap<>();
		for (Entry<Trio<A,B,C>,T> entry : getAllEntriesC(c)) {
			res.put(new Duet<A,B>(entry.getKey().a, entry.getKey().b) , entry.getValue());
		}
		return res;
	}
	
	public Map<C,T> getSubMapAB(A a, B b) {
		Map<C,T> res = new HashMap<>();
		for (Entry<Trio<A,B,C>,T> entry : getAllEntriesAB(a, b)) {
			res.put(entry.getKey().c, entry.getValue());
		}
		return res;
	}
	
	public Map<A,T> getSubMapBC(B b, C c) {
		Map<A,T> res = new HashMap<>();
		for (Entry<Trio<A,B,C>,T> entry : getAllEntriesBC(b, c)) {
			res.put(entry.getKey().a, entry.getValue());
		}
		return res;
	}
	
	private Trio<A,B,C> getKey(A a, B b, C c) {
		return new Trio<A,B,C>(a, b, c);
	}
}
