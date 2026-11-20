package com.nguyenanhvu.collections.tuple.impl;

public class Duet<A,B> extends Solo<A> {
	
	public final B b;
	
	public Duet(A a, B b) {
		super(a);
		this.b = b;
	}

	@Override
	public int size() {
		return 2;
	}

	@Override
	public Object[] toArray() {
		return new Object[]{a, b};
	}

	@Override
	public Duet<A,B> copy() {
		return new Duet<>(a, b);
	}
	
	@Override
	public Duet<A,B> withA(A a) {
		return new Duet<A,B>(a, b);
	}
	
	public Duet<A,B> withB(B b) {
		return new Duet<A,B>(a, b);
	}

	@Override
	public Object get(int index) {
		switch (index) {
		case 0:
			return a;
		case 1:
		case -1:
			return b;
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

	@Override
	public <X> Trio<?,?,?> push(X element) {
		return new Trio<X,A,B>(element, a, b);
	}

	@Override
	public <X> Trio<?,?,?> add(X element) {
		return new Trio<A,B,X>(a, b, element);
	}

	@Override
	public Solo<?> pop() throws IndexOutOfBoundsException {
		return new Solo<A>(a);
	}

	@Override
	public Solo<?> popLeft() throws IndexOutOfBoundsException {
		return new Solo<B>(b);
	}

	@Override
	public <X> Trio<?,?,?> insert(int index, X element) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return push(element);
		case 1:
		case -2:
			return new Trio<A,X,B>(a, element, b);
		case -1:
		case 2:
			return add(element);
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

	@Override
	public Solo<?> remove(int index) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return new Solo<B>(b);
		case 1:
		case -1:
			return new Solo<A>(a);
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}
	
}
