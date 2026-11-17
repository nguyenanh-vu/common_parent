package com.nguyenanhvu.collections.tuple;

public class Quintet<A,B,C,D,E> extends Quartet<A,B,C,D> {

	public final E e;
	
	public Quintet(A a, B b, C c, D d, E e) {
		super(a, b, c, d);
		this.e = e;
	}

	@Override
	public int size() {
		return 5;
	}

	@Override
	public Object[] toArray() {
		return new Object[]{a, b, c, d, e};
	}

	@Override
	public Quintet<A,B,C,D,E> copy() {
		return new Quintet<>(a, b, c, d, e);
	}

	@Override
	public Quintet<A,B,C,D,E> withA(A a) {
		return new Quintet<A,B,C,D,E>(a, b, c, d, e);
	}

	@Override
	public Quintet<A,B,C,D,E> withB(B b) {
		return new Quintet<A,B,C,D,E>(a, b, c, d, e);
	}

	@Override
	public Quintet<A,B,C,D,E> withC(C c) {
		return new Quintet<A,B,C,D,E>(a, b, c, d, e);
	}

	@Override
	public Quintet<A,B,C,D,E> withD(D d) {
		return new Quintet<A,B,C,D,E>(a, b, c, d, e);
	}

	public Quintet<A,B,C,D,E> withE(E e) {
		return new Quintet<A,B,C,D,E>(a, b, c, d, e);
	}
	
	@Override
	public Quartet<A,C,D,E> dropB() {
		return new Quartet<A,C,D,E>(a, c, d, e);
	}

	@Override
	public Quartet<A,B,D,E> dropC() {
		return new Quartet<A,B,D,E>(a, b, d, e);
	}

	public Quartet<A,B,C,E> dropD() {
		return new Quartet<A,B,C,E>(a, b, c, e);
	}

	@Override
	public Object get(int index) {
		switch (index) {
		case 0:
			return a;
		case 1:
		case -4:
			return b;
		case 2:
		case -3:
			return c;
		case 3:
		case -2:
			return d;
		case 4:
		case -1:
			return e;
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

	@Override
	public <X> Tuple push(X element) {
		return new Sextet<X,A,B,C,D,E>(element, a, b, c, d, e);
	}

	@Override
	public <X> Tuple add(X element) {
		return new Sextet<A,B,C,D,E,X>(a, b, c, d, e, element);
	}

	@Override
	public Tuple pop() throws IndexOutOfBoundsException {
		return new Quartet<A,B,C,D>(a, b, c, d);
	}

	@Override
	public Tuple popLeft() throws IndexOutOfBoundsException {
		return new Quartet<B,C,D,E>(b, c, d, e);
	}

	@Override
	public <X> Tuple insert(int index, X element) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return push(element);
		case 1:
		case -5:
			return new Sextet<A,X,B,C,D,E>(a, element, b, c, d, e);
		case 2:
		case -4:
			return new Sextet<A,B,X,C,D,E>(a, b, element, c, d, e);
		case 3:
		case -3:
			return new Sextet<A,B,C,X,D,E>(a, b, c, element, d, e);
		case 4:
		case -2:
			return new Sextet<A,B,C,D,X,E>(a, b, c, d, element, e);
		case -1:
		case 5:
			return add(element);
		default:
			return super.insert(index, element);
		}
	}

	@Override
	public Tuple remove(int index) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return new Quartet<B,C,D,E>(b, c, d, e);
		case 1:
		case -4:
			return new Quartet<A,C,D,E>(a, c, d, e);
		case 2:
		case -3:
			return new Quartet<A,B,D,E>(a, b, d, e);
		case 3:
		case -2:
			return new Quartet<A,B,C,E>(a, b, c, e);
		case 4:
		case -1:
			return new Quartet<A,B,C,D>(a, b, c, d);
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

}
