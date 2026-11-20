package com.nguyenanhvu.collections.tuplemap;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.nguyenanhvu.collections.tuple.impl.Tuple;

public abstract class TupleMap<K extends Tuple,T> implements Map<K,T> {
	
	protected final Map<K,T> map;
	
	protected TupleMap() {
		this.map = new HashMap<>();
	}
	
	protected TupleMap(Map<K,T> map) {
		this.map = map;
	}
	
	public Collection<T> getAll(Tuple filter) {
		return getAll(filter, 0);
	}
	
	public Collection<T> getAll(Tuple filter, int offset) {
		return entrySet()
				.stream()
				.filter(e -> e.getKey().matches(filter, offset))
				.map(Entry::getValue)
				.collect(Collectors.toList());
	}
	
	public Collection<K> getAllKeys(Tuple filter) {
		return getAllKeys(filter, 0);
	}
	
	public Collection<K> getAllKeys(Tuple filter, int offset) {
		return keySet()
				.stream()
				.filter(k -> k.matches(filter, offset))
				.collect(Collectors.toList());
	}
	
	public Collection<Entry<K,T>> getAllEntries(Tuple filter) {
		return getAllEntries(filter, 0);
	}
	
	public Collection<Entry<K,T>> getAllEntries(Tuple filter, int offset) {
		return entrySet()
				.stream()
				.filter(e -> e.getKey().matches(filter, offset))
				.collect(Collectors.toList());
	}
	
	public Collection<T> removeAll(Tuple filter) {
		return removeAll(filter, 0);
	}
	
	public Collection<T> removeAll(Tuple filter, int offset) {
		Collection<K> toRemove = getAllKeys(filter, offset);
		Set<T> res = new HashSet<>();
		for (K k : toRemove) {
			res.add(map.remove(k));
		}
		return res;
	}

	// Map<K,T> implementations

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public Set<Entry<K,T>> entrySet() {
		return map.entrySet();
	}

	@Override
	public T get(Object key) {
		return map.get(key);
	}
	
	public T getWithDefault(Object key, T def) {
		T res = map.get(key);
		return res == null ? def : res;
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		return map.keySet();
	}

	@Override
	public T put(K key, T value) {
		return map.put(key, value);
	}

	@Override
	public void putAll(Map<? extends K,? extends T> m) {
		map.putAll(m);
	}

	@Override
	public T remove(Object key) {
		return map.remove(key);
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Collection<T> values() {
		return map.values();
	}

}
