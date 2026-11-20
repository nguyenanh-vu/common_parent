package com.nguyenanhvu.collections.tuple;

import java.util.Collection;
import java.util.Iterator;

public interface ITuple {
	
	public int size();
	
	public Object[] toArray();
	
	public Object[] toArray(Object[] a);
	
	public ITuple copy();
	
	public Object get(int index);
	
	public <X> ITuple push(X element);
	
	public <X> ITuple add(X element);
	
	public ITuple pop() throws IndexOutOfBoundsException;
	
	public ITuple popLeft() throws IndexOutOfBoundsException;
	
	public <X> ITuple insert(int index, X element) throws IndexOutOfBoundsException;
	
	public ITuple remove(int index) throws IndexOutOfBoundsException;
	
	public boolean matches(ITuple other);
	
	public boolean matches(ITuple other, int offset);

	public <T> ITuple addAll(Collection<T> c);

	public boolean contains(Object o);

	public boolean containsAll(Collection<?> c);

	public boolean isEmpty();

	public Iterator<Object> iterator();

	public <T> ITuple removeAll(Collection<T> c);

	public <T> ITuple retainAll(Collection<T> c);

}
