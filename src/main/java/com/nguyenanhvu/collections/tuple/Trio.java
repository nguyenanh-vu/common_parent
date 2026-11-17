package com.nguyenanhvu.collections.tuple;

public class Trio<A,B,C> extends Duet<A,B> {
	
	public final C c;
	
	public Trio(A a, B b, C c) {
		super(a, b);
		this.c = c;
	}

	@Override
	public int size() {
		return 3;
	}

	@Override
	public Object[] toArray() {
		return new Object[]{a, b, c};
	}

	@Override
	public Trio<A,B,C> copy() {
		return new Trio<>(a, b, c);
	}

	@Override
	public Trio<A,B,C> withA(A a) {
		return new Trio<A,B,C>(a, b, c);
	}

	@Override
	public Trio<A,B,C> withB(B b) {
		return new Trio<A,B,C>(a, b, c);
	}
	
	public Trio<A,B,C> withC(C c) {
		return new Trio<A,B,C>(a, b, c);
	}
	
	public Duet<A,C> dropB() {
		return new Duet<A,C>(a, c);
	}

	@Override
	public Object get(int index) {
		switch (index) {
		case 0:
			return a;
		case 1:
		case -2:
			return b;
		case 2:
		case -1:
			return c;
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

	@Override
	public <X> Tuple push(X element) {
		return new Quartet<X,A,B,C>(element, a, b, c);
	}

	@Override
	public <X> Tuple add(X element) {
		return new Quartet<A,B,C,X>(a, b, c, element);
	}

	@Override
	public Tuple pop() throws IndexOutOfBoundsException {
		return new Duet<A,B>(a, b);
	}

	@Override
	public Tuple popLeft() throws IndexOutOfBoundsException {
		return new Duet<B,C>(b, c);
	}

	@Override
	public <X> Tuple insert(int index, X element) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return push(element);
		case 1:
		case -3:
			return new Quartet<A,X,B,C>(a, element, b, c);
		case 2:
		case -2:
			return new Quartet<A,B,X,C>(a, b, element, c);
		case -1:
		case 3:
			return add(element);
		default:
			return super.insert(index, element);
		}
	}

	@Override
	public Tuple remove(int index) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return new Duet<B,C>(b, c);
		case 1:
		case -2:
			return new Duet<A,C>(a, c);
		case 2:
		case -1:
			return new Duet<A,B>(a, b);
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}
}