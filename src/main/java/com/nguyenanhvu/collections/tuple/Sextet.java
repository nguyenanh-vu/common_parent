package com.nguyenanhvu.collections.tuple;

public class Sextet<A,B,C,D,E,F> extends Quintet<A,B,C,D,E> {
	
	public final F f;

	public Sextet(A a, B b, C c, D d, E e, F f) {
		super(a, b, c, d, e);
		this.f = f;
	}

	@Override
	public int size() {
		return 6;
	}

	@Override
	public Object[] toArray() {
		return new Object[]{a, b, c, d, e, f};
	}

	@Override
	public Sextet<A,B,C,D,E,F> copy() {
		return new Sextet<>(a, b, c, d, e, f);
	}

	@Override
	public Sextet<A,B,C,D,E,F> withA(A a) {
		return new Sextet<A,B,C,D,E,F>(a, b, c, d, e, f);
	}

	@Override
	public Sextet<A,B,C,D,E,F> withB(B b) {
		return new Sextet<A,B,C,D,E,F>(a, b, c, d, e, f);
	}

	@Override
	public Sextet<A,B,C,D,E,F> withC(C c) {
		return new Sextet<A,B,C,D,E,F>(a, b, c, d, e, f);
	}

	@Override
	public Sextet<A,B,C,D,E,F> withD(D d) {
		return new Sextet<A,B,C,D,E,F>(a, b, c, d, e, f);
	}

	@Override
	public Sextet<A,B,C,D,E,F> withE(E e) {
		return new Sextet<A,B,C,D,E,F>(a, b, c, d, e, f);
	}

	public Sextet<A,B,C,D,E,F> withF(F f) {
		return new Sextet<A,B,C,D,E,F>(a, b, c, d, e, f);
	}
	
	@Override
	public Quintet<A,C,D,E,F> dropB() {
		return new Quintet<A,C,D,E,F>(a, c, d, e, f);
	}

	@Override
	public Quintet<A,B,D,E,F> dropC() {
		return new Quintet<A,B,D,E,F>(a, b, d, e, f);
	}

	@Override
	public Quintet<A,B,C,E,F> dropD() {
		return new Quintet<A,B,C,E,F>(a, b, c, e, f);
	}

	public Quintet<A,B,C,D,F> dropE() {
		return new Quintet<A,B,C,D,F>(a, b, c, d, f);
	}

	@Override
	public Object get(int index) {
		switch (index) {
		case 0:
			return a;
		case 1:
		case -5:
			return b;
		case 2:
		case -4:
			return c;
		case 3:
		case -3:
			return d;
		case 4:
		case -2:
			return e;
		case 5:
		case -1:
			return f;
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

	@Override
	public <X> Tuple push(X element) {
		return new Septet<X,A,B,C,D,E,F>(element, a, b, c, d, e, f);
	}

	@Override
	public <X> Tuple add(X element) {
		return new Septet<A,B,C,D,E,F,X>(a, b, c, d, e, f, element);
	}

	@Override
	public Tuple pop() throws IndexOutOfBoundsException {
		return new Quintet<A,B,C,D,E>(a, b, c, d, e);
	}

	@Override
	public Tuple popLeft() throws IndexOutOfBoundsException {
		return new Quintet<B,C,D,E,F>(b, c, d, e, f);
	}

	@Override
	public <X> Tuple insert(int index, X element) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return push(element);
		case 1:
		case -6:
			return new Septet<A,X,B,C,D,E,F>(a, element, b, c, d, e, f);
		case 2:
		case -5:
			return new Septet<A,B,X,C,D,E,F>(a, b, element, c, d, e, f);
		case 3:
		case -4:
			return new Septet<A,B,C,X,D,E,F>(a, b, c, element, d, e, f);
		case 4:
		case -3:
			return new Septet<A,B,C,D,X,E,F>(a, b, c, d, element, e, f);
		case 5:
		case -2:
			return new Septet<A,B,C,D,E,X,F>(a, b, c, d, e, element, f);
		case -1:
		case 6:
			return add(element);
		default:
			return super.insert(index, element);
		}
	}

	@Override
	public Tuple remove(int index) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return new Quintet<B,C,D,E,F>(b, c, d, e, f);
		case 1:
		case -5:
			return new Quintet<A,C,D,E,F>(a, c, d, e, f);
		case 2:
		case -4:
			return new Quintet<A,B,D,E,F>(a, b, d, e, f);
		case 3:
		case -3:
			return new Quintet<A,B,C,E,F>(a, b, c, e, f);
		case 4:
		case -2:
			return new Quintet<A,B,C,D,F>(a, b, c, d, f);
		case 5:
		case -1:
			return new Quintet<A,B,C,D,E>(a, b, c, d, e);
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

}
