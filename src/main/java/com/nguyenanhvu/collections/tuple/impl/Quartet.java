package com.nguyenanhvu.collections.tuple.impl;

public class Quartet<A,B,C,D> extends Trio<A,B,C> {
	
	public final D d;
	
	public Quartet(A a, B b, C c, D d) {
		super(a, b, c);
		this.d = d;
	}

	@Override
	public int size() {
		return 4;
	}

	@Override
	public Object[] toArray() {
		return new Object[]{a, b, c, d};
	}

	@Override
	public Quartet<A,B,C,D> copy() {
		return new Quartet<>(a, b, c, d);
	}

	@Override
	public Quartet<A,B,C,D> withA(A a) {
		return new Quartet<A,B,C,D>(a, b, c, d);
	}

	@Override
	public Quartet<A,B,C,D> withB(B b) {
		return new Quartet<A,B,C,D>(a, b, c, d);
	}

	@Override
	public Quartet<A,B,C,D> withC(C c) {
		return new Quartet<A,B,C,D>(a, b, c, d);
	}
	
	public Quartet<A,B,C,D> withD(D d) {
		return new Quartet<A,B,C,D>(a, b, c, d);
	}
	
	@Override
	public Trio<A,C,D> dropB() {
		return new Trio<A,C,D>(a, c, d);
	}
	
	public Trio<A,B,D> dropC() {
		return new Trio<A,B,D>(a, b, d);
	}

	@Override
	public Object get(int index) {
		switch (index) {
		case 0:
			return a;
		case 1:
		case -3:
			return b;
		case 2:
		case -2:
			return c;
		case 3:
		case -1:
			return d;
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

	@Override
	public <X> Quintet<?,?,?,?,?> push(X element) {
		return new Quintet<X,A,B,C,D>(element, a, b, c, d);
	}

	@Override
	public <X> Quintet<?,?,?,?,?> add(X element) {
		return new Quintet<A,B,C,D,X>(a, b, c, d, element);
	}

	@Override
	public Trio<?,?,?> pop() throws IndexOutOfBoundsException {
		return new Trio<A,B,C>(a, b, c);
	}

	@Override
	public Trio<?,?,?> popLeft() throws IndexOutOfBoundsException {
		return new Trio<B,C,D>(b, c, d);
	}

	@Override
	public <X> Quintet<?,?,?,?,?> insert(int index, X element) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return push(element);
		case 1:
		case -4:
			return new Quintet<A,X,B,C,D>(a, element, b, c, d);
		case 2:
		case -3:
			return new Quintet<A,B,X,C,D>(a, b, element, c, d);
		case 3:
		case -2:
			return new Quintet<A,B,C,X,D>(a, b, c, element, d);
		case -1:
		case 4:
			return add(element);
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

	@Override
	public Trio<?,?,?> remove(int index) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return new Trio<B,C,D>(b, c, d);
		case 1:
		case -3:
			return new Trio<A,C,D>(a, c, d);
		case 2:
		case -2:
			return new Trio<A,B,D>(a, b, d);
		case 3:
		case -1:
			return new Trio<A,B,C>(a, b, c);
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

}
