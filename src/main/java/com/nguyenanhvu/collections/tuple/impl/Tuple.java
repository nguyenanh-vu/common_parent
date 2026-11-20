package com.nguyenanhvu.collections.tuple.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import com.nguyenanhvu.collections.tuple.ITuple;

public abstract class Tuple implements ITuple {

	@Override
	public Object[] toArray(Object[] a) {
		if (a.length <= size()) {
			return toArray();
		}
		System.arraycopy(toArray(), 0, a, 0, size());
		return a;
	}
	
	@Override
	public int hashCode() {
		int res = 7;
		for (Object o : toArray()) {
			res = res * 31 + o.hashCode();
		}
		return res;
	}
	
	@Override
	public boolean equals(Object o) {
		if ( !(o instanceof Tuple)) {
			return false;
		}
		
		Tuple asTuple = (Tuple) o;
		if (asTuple.size() != this.size()) {
			return false;
		}
		
		for (int i = 0; i < this.size(); i++) {
			if (!Objects.equals(get(i), asTuple.get(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean matches(ITuple other) {
		return matches(other, 0);
	}

	@Override
	public boolean matches(ITuple other, int offset) {
		int len = other.size();
		if (len + offset > size() || offset < 0) {
			return false;
		}
		
		for (int i = 0; i < len; i++) {
			if (!Objects.equals(get(i + offset), other.get(i)) ) {
				return false;
			}
		}
		return true;
	}

	@Override
	public <T> ITuple addAll(Collection<T> c) {
		ITuple res = this;
		for (T o : c) {
			res = res.add(o);
		}
		return res;
	}

	@Override
	public boolean contains(Object o) {
		for (Object o1 : toArray()) {
			if (Objects.equals(o1, o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		Set<Object> thisSet = new HashSet<>(Arrays.asList(toArray()));
		for (Object o : c) {
			if (!thisSet.contains(o)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		if (size()== 0) {
			return true;
		}
		for (Object o : toArray()) {
			if (o != null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Iterator<Object> iterator() {
		return Arrays.asList(toArray()).iterator();
	}

	@Override
	public <T> ITuple removeAll(Collection<T> c) {
		ITuple res = this;
		Object[] elements = toArray();
		for (int i = size() - 1; i >= 0; i--) {
			if (c.contains(elements[i])) {
				res = res.remove(i);
			}
		}
		return res;
	}

	@Override
	public <T> ITuple retainAll(Collection<T> c) {
		ITuple res = this;
		Object[] elements = toArray();
		for (int i = size() - 1; i >= 0; i--) {
			if (!c.contains(elements[i])) {
				res = res.remove(i);
			}
		}
		return res;
	}
	
}