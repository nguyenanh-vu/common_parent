package com.nguyenanhvu.collections.tuplemap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.nguyenanhvu.collections.tuple.Duet;
import com.nguyenanhvu.collections.tuple.Quartet;
import com.nguyenanhvu.collections.tuple.Solo;
import com.nguyenanhvu.collections.tuple.Trio;

public class QuartetMap<A,B,C,D,T> extends TupleMap<Quartet<A,B,C,D>,T> {
	
	public QuartetMap() {
		super();
	}
	
	public QuartetMap(Map<Quartet<A,B,C,D>, T> map) {
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
	
	public Collection<T> getAllA(A a) {
		return getAll(new Solo<A>(a));
	}
	
	public Collection<T> getAllB(B b) {
		return getAll(new Solo<B>(b), 1);
	}
	
	public Collection<T> getAllC(C c) {
		return getAll(new Solo<C>(c), 2);
	}
	
	public Collection<T> getAllD(D d) {
		return getAll(new Solo<D>(d), 3);
	}
	
	public Collection<T> getAllAB(A a, B b) {
		return getAll(new Duet<A,B>(a, b));
	}
	
	public Collection<T> getAllBC(B b, C c) {
		return getAll(new Duet<B,C>(b, c), 1);
	}
	
	public Collection<T> getAllCD(C c, D d) {
		return getAll(new Duet<C,D>(c, d), 2);
	}
	
	public Collection<T> getAllABC(A a, B b, C c) {
		return getAll(new Trio<A,B,C>(a, b, c));
	}
	
	public Collection<T> getAllBCD(B b, C c, D d) {
		return getAll(new Trio<B,C,D>(b, c, d), 1);
	}
	
	public Collection<Entry<Quartet<A,B,C,D>,T>> getAllEntriesA(A a) {
		return getAllEntries(new Solo<A>(a));
	}
	
	public Collection<Entry<Quartet<A,B,C,D>,T>> getAllEntriesB(B b) {
		return getAllEntries(new Solo<B>(b), 1);
	}
	
	public Collection<Entry<Quartet<A,B,C,D>,T>> getAllEntriesC(C c) {
		return getAllEntries(new Solo<C>(c), 2);
	}
	
	public Collection<Entry<Quartet<A,B,C,D>,T>> getAllEntriesD(D d) {
		return getAllEntries(new Solo<D>(d), 3);
	}
	
	public Collection<Entry<Quartet<A,B,C,D>,T>> getAllEntriesAB(A a, B b) {
		return getAllEntries(new Duet<A,B>(a, b));
	}
	
	public Collection<Entry<Quartet<A,B,C,D>,T>> getAllEntriesBC(B b, C c) {
		return getAllEntries(new Duet<B,C>(b, c), 1);
	}
	
	public Collection<Entry<Quartet<A,B,C,D>,T>> getAllEntriesCD(C c, D d) {
		return getAllEntries(new Duet<C,D>(c, d), 2);
	}
	
	public Collection<Entry<Quartet<A,B,C,D>,T>> getAllEntriesABC(A a, B b, C c) {
		return getAllEntries(new Trio<A,B,C>(a, b, c));
	}
	
	public Collection<Entry<Quartet<A,B,C,D>,T>> getAllEntriesBCD(B b, C c, D d) {
		return getAllEntries(new Trio<B,C,D>(b, c, d), 1);
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
	
	public Collection<T> removeAllD(D d) {
		return removeAll(new Solo<D>(d), 3);
	}
	
	public Collection<T> removeAllAB(A a, B b) {
		return removeAll(new Duet<A,B>(a, b));
	}
	
	public Collection<T> removeAllBC(B b, C c) {
		return removeAll(new Duet<B,C>(b, c), 1);
	}
	
	public Collection<T> removeAllCD(C c, D d) {
		return removeAll(new Duet<C,D>(c, d), 2);
	}
	
	public Collection<T> removeAllABC(A a, B b, C c) {
		return removeAll(new Trio<A,B,C>(a, b, c));
	}
	
	public Collection<T> removeAllBCD(B b, C c, D d) {
		return removeAll(new Trio<B,C,D>(b, c, d), 1);
	}
	
	public TrioMap<B,C,D,T> getSubMapA(A a) {
		TrioMap<B,C,D,T> res = new TrioMap<>();
		for (Entry<Quartet<A,B,C,D>,T> entry : getAllEntriesA(a)) {
			res.put(new Trio<B,C,D>(entry.getKey().b, entry.getKey().c, entry.getKey().d) , entry.getValue());
		}
		return res;
	}
	
	public TrioMap<A,C,D,T> getSubMapB(B b) {
		TrioMap<A,C,D,T> res = new TrioMap<>();
		for (Entry<Quartet<A,B,C,D>,T> entry : getAllEntriesB(b)) {
			res.put(new Trio<A,C,D>(entry.getKey().a, entry.getKey().c, entry.getKey().d) , entry.getValue());
		}
		return res;
	}
	
	public TrioMap<A,B,D,T> getSubMapC(C c) {
		TrioMap<A,B,D,T> res = new TrioMap<>();
		for (Entry<Quartet<A,B,C,D>,T> entry : getAllEntriesC(c)) {
			res.put(new Trio<A,B,D>(entry.getKey().a, entry.getKey().b, entry.getKey().d) , entry.getValue());
		}
		return res;
	}
	
	public TrioMap<A,B,C,T> getSubMapD(D d) {
		TrioMap<A,B,C,T> res = new TrioMap<>();
		for (Entry<Quartet<A,B,C,D>,T> entry : getAllEntriesD(d)) {
			res.put(new Trio<A,B,C>(entry.getKey().a, entry.getKey().b, entry.getKey().c) , entry.getValue());
		}
		return res;
	}
	
	public DuetMap<C,D,T> getSubMapAB(A a, B b) {
		DuetMap<C,D,T> res = new DuetMap<>();
		for (Entry<Quartet<A,B,C,D>,T> entry : getAllEntriesAB(a, b)) {
			res.put(new Duet<C,D>(entry.getKey().c, entry.getKey().d) , entry.getValue());
		}
		return res;
	}
	
	public DuetMap<A,D,T> getSubMapBC(B b, C c) {
		DuetMap<A,D,T> res = new DuetMap<>();
		for (Entry<Quartet<A,B,C,D>,T> entry : getAllEntriesBC(b, c)) {
			res.put(new Duet<A,D>(entry.getKey().a, entry.getKey().d) , entry.getValue());
		}
		return res;
	}
	
	public DuetMap<A,B,T> getSubMapCD(C c, D d) {
		DuetMap<A,B,T> res = new DuetMap<>();
		for (Entry<Quartet<A,B,C,D>,T> entry : getAllEntriesCD(c, d)) {
			res.put(new Duet<A,B>(entry.getKey().a, entry.getKey().b) , entry.getValue());
		}
		return res;
	}
	
	public Map<D,T> getSubMapABC(A a, B b, C c) {
		Map<D,T> res = new HashMap<>();
		for (Entry<Quartet<A,B,C,D>,T> entry : getAllEntriesABC(a, b, c)) {
			res.put(entry.getKey().d, entry.getValue());
		}
		return res;
	}
	
	public Map<A,T> getSubMapBCD(B b, C c, D d) {
		Map<A,T> res = new HashMap<>();
		for (Entry<Quartet<A,B,C,D>,T> entry : getAllEntriesBCD(b, c, d)) {
			res.put(entry.getKey().a, entry.getValue());
		}
		return res;
	}
	
	private Quartet<A,B,C,D> getKey(A a, B b, C c, D d) {
		return new Quartet<A,B,C,D>(a, b, c, d);
	}
}
