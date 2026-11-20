package com.nguyenanhvu.collections.tuple.impl;

public class Solo<A> extends Empty {
	
	public final A a;
	
	public Solo(A a) {
		this.a = a;
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public Object[] toArray() {
		return new Object[]{a};
	}

	@Override
	public Solo<A> copy() {
		return new Solo<A>(a);
	}
	
	public Solo<A> withA(A a) {
		return new Solo<A>(a);
	}

	@Override
	public Object get(int index) {
		if (index == 0) {
			return a;
		} else {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

	@Override
	public <X> Duet<?,?> push(X element) {
		return new Duet<X,A>(element, a);
	}

	@Override
	public <X> Duet<?,?> add(X element) {
		return new Duet<A,X>(a, element);
	}

	@Override
	public Empty pop() throws IndexOutOfBoundsException {
		return new Empty();
	}

	@Override
	public Empty popLeft() throws IndexOutOfBoundsException {
		return new Empty();
	}

	@Override
	public <X> Duet<?,?> insert(int index, X element) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return push(element);
		case 1:
		case -1:
			return add(element);
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

	@Override
	public Empty remove(int index) throws IndexOutOfBoundsException {
		if (index == 0) {
			return new Empty();
		} else {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}
	
}
