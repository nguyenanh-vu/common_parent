package com.nguyenanhvu.collections.tuple;

public class Septet<A,B,C,D,E,F,G> extends Sextet<A,B,C,D,E,F> {
	
	public final G g;

	public Septet(A a, B b, C c, D d, E e, F f, G g) {
		super(a, b, c, d, e, f);
		this.g = g;
	}

	@Override
	public int size() {
		return 7;
	}

	@Override
	public Object[] toArray() {
		return new Object[]{a, b, c, d, e, f, g};
	}

	@Override
	public Septet<A,B,C,D,E,F,G> copy() {
		return new Septet<>(a, b, c, d, e, f, g);
	}

	@Override
	public Septet<A,B,C,D,E,F,G> withA(A a) {
		return new Septet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
	}

	@Override
	public Septet<A,B,C,D,E,F,G> withB(B b) {
		return new Septet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
	}

	@Override
	public Septet<A,B,C,D,E,F,G> withC(C c) {
		return new Septet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
	}

	@Override
	public Septet<A,B,C,D,E,F,G> withD(D d) {
		return new Septet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
	}

	@Override
	public Septet<A,B,C,D,E,F,G> withE(E e) {
		return new Septet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
	}

	@Override
	public Septet<A,B,C,D,E,F,G> withF(F f) {
		return new Septet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
	}

	public Septet<A,B,C,D,E,F,G> withG(G g) {
		return new Septet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
	}
	
	@Override
	public Sextet<A,C,D,E,F,G> dropB() {
		return new Sextet<A,C,D,E,F,G>(a, c, d, e, f, g);
	}

	@Override
	public Sextet<A,B,D,E,F,G> dropC() {
		return new Sextet<A,B,D,E,F,G>(a, b, d, e, f, g);
	}

	@Override
	public Sextet<A,B,C,E,F,G> dropD() {
		return new Sextet<A,B,C,E,F,G>(a, b, c, e, f, g);
	}

	@Override
	public Sextet<A,B,C,D,F,G> dropE() {
		return new Sextet<A,B,C,D,F,G>(a, b, c, d, f, g);
	}

	public Sextet<A,B,C,D,E,G> dropF() {
		return new Sextet<A,B,C,D,E,G>(a, b, c, d, e, g);
	}

	@Override
	public Object get(int index) {
		switch (index) {
		case 0:
			return a;
		case 1:
		case -6:
			return b;
		case 2:
		case -5:
			return c;
		case 3:
		case -4:
			return d;
		case 4:
		case -3:
			return e;
		case 5:
		case -2:
			return f;
		case 6:
		case -1:
			return g;
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

	@Override
	public <X> Tuple push(X element) {
		return new Octet<X,A,B,C,D,E,F,G>(element, a, b, c, d, e, f, g);
	}

	@Override
	public <X> Tuple add(X element) {
		return new Octet<A,B,C,D,E,F,G,X>(a, b, c, d, e, f, g, element);
	}

	@Override
	public Tuple pop() throws IndexOutOfBoundsException {
		return new Sextet<A,B,C,D,E,F>(a, b, c, d, e, f);
	}

	@Override
	public Tuple popLeft() throws IndexOutOfBoundsException {
		return new Sextet<B,C,D,E,F,G>(b, c, d, e, f, g);
	}

	@Override
	public <X> Tuple insert(int index, X element) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return push(element);
		case 1:
		case -7:
			return new Octet<A,X,B,C,D,E,F,G>(a, element, b, c, d, e, f, g);
		case 2:
		case -6:
			return new Octet<A,B,X,C,D,E,F,G>(a, b, element, c, d, e, f, g);
		case 3:
		case -5:
			return new Octet<A,B,C,X,D,E,F,G>(a, b, c, element, d, e, f, g);
		case 4:
		case -4:
			return new Octet<A,B,C,D,X,E,F,G>(a, b, c, d, element, e, f, g);
		case 5:
		case -3:
			return new Octet<A,B,C,D,E,X,F,G>(a, b, c, d, e, element, f, g);
		case 6:
		case -2:
			return new Octet<A,B,C,D,E,F,X,G>(a, b, c, d, e, f, element, g);
		case -1:
		case 7:
			return add(element);
		default:
			return super.insert(index, element);
		}
	}

	@Override
	public Tuple remove(int index) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return new Sextet<B,C,D,E,F,G>(b, c, d, e, f, g);
		case 1:
		case -6:
			return new Sextet<A,C,D,E,F,G>(a, c, d, e, f, g);
		case 2:
		case -5:
			return new Sextet<A,B,D,E,F,G>(a, b, d, e, f, g);
		case 3:
		case -4:
			return new Sextet<A,B,C,E,F,G>(a, b, c, e, f, g);
		case 4:
		case -3:
			return new Sextet<A,B,C,D,F,G>(a, b, c, d, f, g);
		case 5:
		case -2:
			return new Sextet<A,B,C,D,E,G>(a, b, c, d, e, g);
		case 6:
		case -1:
			return new Sextet<A,B,C,D,E,F>(a, b, c, d, e, f);
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

}
