package com.nguyenanhvu.collections.tuple;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public abstract class Tuple {
	
	public abstract int size();
	
	public abstract Object[] toArray();
	
	public abstract Tuple copy();

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
		
		Object[] elements = this.toArray();
		Object[] otherElements = asTuple.toArray();
		
		for (int i = 0; i < this.size(); i++) {
			if (!Objects.equals(elements[i], otherElements[i])) {
				return false;
			}
		}
		return true;
	}
	public boolean matches(Tuple other) {
		return matches(other, 0);
	}
	
	public boolean matches(Tuple other, int offset) {
		int len = other.size();
		if (len + offset > size() || offset < 0) {
			return false;
		}
		Object[] elements = toArray();
		Object[] otherElements = toArray();
		
		for (int i = 0; i < len; i++) {
			if (!Objects.equals(elements[i + offset], otherElements[i]) ) {
				return false;
			}
		}
		return true;
	}

	public <T> Tuple addAll(Collection<T> c) {
		Tuple res = this;
		for (T o : c) {
			res = res.add(o);
		}
		return res;
	}

	public boolean contains(Object o) {
		for (Object o1 : toArray()) {
			if (Objects.equals(o1, o)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		Set<Object> thisSet = new HashSet<>(Arrays.asList(toArray()));
		for (Object o : c) {
			if (!thisSet.contains(o)) {
				return false;
			}
		}
		return true;
	}

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

	public Iterator<Object> iterator() {
		return Arrays.asList(toArray()).iterator();
	}

	public <T> Tuple removeAll(Collection<T> c) {
		Tuple res = this;
		Object[] elements = toArray();
		for (int i = size() - 1; i >= 0; i--) {
			if (c.contains(elements[i])) {
				res = res.remove(i);
			}
		}
		return res;
	}

	public <T> Tuple retainAll(Collection<T> c) {
		Tuple res = this;
		Object[] elements = toArray();
		for (int i = size() - 1; i >= 0; i--) {
			if (!c.contains(elements[i])) {
				res = res.remove(i);
			}
		}
		return res;
	}
	
	public abstract Object get(int index);
	
	public abstract <X> Tuple push(X element);
	
	public abstract <X> Tuple add(X element);
	
	public abstract Tuple pop() throws IndexOutOfBoundsException;
	
	public abstract Tuple popLeft() throws IndexOutOfBoundsException;
	
	public abstract <X> Tuple insert(int index, X element) throws IndexOutOfBoundsException;
	
	public abstract Tuple remove(int index) throws IndexOutOfBoundsException;
}