package com.nguyenanhvu.collections.tuple;

public class Octet<A,B,C,D,E,F,G,H> extends Septet<A,B,C,D,E,F,G> {
	
	public final H h;

	public Octet(A a, B b, C c, D d, E e, F f, G g, H h) {
		super(a, b, c, d, e, f, g);
		this.h = h;
	}

	@Override
	public int size() {
		return 8;
	}

	@Override
	public Object[] toArray() {
		return new Object[]{a, b, c, d, e, f, g, h};
	}

	@Override
	public Octet<A,B,C,D,E,F,G,H> copy() {
		return new Octet<>(a, b, c, d, e, f, g, h);
	}

	@Override
	public Octet<A,B,C,D,E,F,G,H> withA(A a) {
		return new Octet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	@Override
	public Octet<A,B,C,D,E,F,G,H> withB(B b) {
		return new Octet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	@Override
	public Octet<A,B,C,D,E,F,G,H> withC(C c) {
		return new Octet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	@Override
	public Octet<A,B,C,D,E,F,G,H> withD(D d) {
		return new Octet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	@Override
	public Octet<A,B,C,D,E,F,G,H> withE(E e) {
		return new Octet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	@Override
	public Octet<A,B,C,D,E,F,G,H> withF(F f) {
		return new Octet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	@Override
	public Octet<A,B,C,D,E,F,G,H> withG(G g) {
		return new Octet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	public Octet<A,B,C,D,E,F,G,H> withH(H h) {
		return new Octet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}
	
	@Override
	public Septet<A,C,D,E,F,G,H> dropB() {
		return new Septet<A,C,D,E,F,G,H>(a, c, d, e, f, g, h);
	}

	@Override
	public Septet<A,B,D,E,F,G,H> dropC() {
		return new Septet<A,B,D,E,F,G,H>(a, b, d, e, f, g, h);
	}

	@Override
	public Septet<A,B,C,E,F,G,H> dropD() {
		return new Septet<A,B,C,E,F,G,H>(a, b, c, e, f, g, h);
	}

	@Override
	public Septet<A,B,C,D,F,G,H> dropE() {
		return new Septet<A,B,C,D,F,G,H>(a, b, c, d, f, g, h);
	}

	@Override
	public Septet<A,B,C,D,E,G,H> dropF() {
		return new Septet<A,B,C,D,E,G,H>(a, b, c, d, e, g, h);
	}

	public Septet<A,B,C,D,E,F,H> dropG() {
		return new Septet<A,B,C,D,E,F,H>(a, b, c, d, e, f, h);
	}

	@Override
	public Object get(int index) {
		switch (index) {
		case 0:
			return a;
		case 1:
		case -7:
			return b;
		case 2:
		case -6:
			return c;
		case 3:
		case -5:
			return d;
		case 4:
		case -4:
			return e;
		case 5:
		case -3:
			return f;
		case 6:
		case -2:
			return g;
		case 7:
		case -1:
			return h;
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

	@Override
	public <X> Tuple push(X element) {
		return new Nonet<X,A,B,C,D,E,F,G,H>(element, a, b, c, d, e, f, g, h);
	}

	@Override
	public <X> Tuple add(X element) {
		return new Nonet<A,B,C,D,E,F,G,H,X>(a, b, c, d, e, f, g, h, element);
	}

	@Override
	public Tuple pop() throws IndexOutOfBoundsException {
		return new Septet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
	}

	@Override
	public Tuple popLeft() throws IndexOutOfBoundsException {
		return new Septet<B,C,D,E,F,G,H>(b, c, d, e, f, g, h);
	}

	@Override
	public <X> Tuple insert(int index, X element) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return push(element);
		case 1:
		case -8:
			return new Nonet<A,X,B,C,D,E,F,G,H>(a, element, b, c, d, e, f, g, h);
		case 2:
		case -7:
			return new Nonet<A,B,X,C,D,E,F,G,H>(a, b, element, c, d, e, f, g, h);
		case 3:
		case -6:
			return new Nonet<A,B,C,X,D,E,F,G,H>(a, b, c, element, d, e, f, g, h);
		case 4:
		case -5:
			return new Nonet<A,B,C,D,X,E,F,G,H>(a, b, c, d, element, e, f, g, h);
		case 5:
		case -4:
			return new Nonet<A,B,C,D,E,X,F,G,H>(a, b, c, d, e, element, f, g, h);
		case 6:
		case -3:
			return new Nonet<A,B,C,D,E,F,X,G,H>(a, b, c, d, e, f, element, g, h);
		case 7:
		case -2:
			return new Nonet<A,B,C,D,E,F,G,X,H>(a, b, c, d, e, f, g, element, h);
		case -1:
		case 8:
			return add(element);
		default:
			return super.insert(index, element);
		}
	}

	@Override
	public Tuple remove(int index) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return new Septet<B,C,D,E,F,G,H>(b, c, d, e, f, g, h);
		case 1:
		case -7:
			return new Septet<A,C,D,E,F,G,H>(a, c, d, e, f, g, h);
		case 2:
		case -6:
			return new Septet<A,B,D,E,F,G,H>(a, b, d, e, f, g, h);
		case 3:
		case -5:
			return new Septet<A,B,C,E,F,G,H>(a, b, c, e, f, g, h);
		case 4:
		case -4:
			return new Septet<A,B,C,D,F,G,H>(a, b, c, d, f, g, h);
		case 5:
		case -3:
			return new Septet<A,B,C,D,E,G,H>(a, b, c, d, e, g, h);
		case 6:
		case -2:
			return new Septet<A,B,C,D,E,F,H>(a, b, c, d, e, f, h);
		case 7:
		case -1:
			return new Septet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

}
