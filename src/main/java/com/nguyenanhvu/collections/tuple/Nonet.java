package com.nguyenanhvu.collections.tuple;

public class Nonet<A,B,C,D,E,F,G,H,I> extends Octet<A,B,C,D,E,F,G,H> {
	
	public final I i;

	public Nonet(A a, B b, C c, D d, E e, F f, G g, H h, I i) {
		super(a, b, c, d, e, f, g, h);
		this.i = i;
	}

	@Override
	public int size() {
		return 9;
	}

	@Override
	public Object[] toArray() {
		return new Object[]{a, b, c, d, e, f, g, h, i};
	}

	@Override
	public Nonet<A,B,C,D,E,F,G,H,I> copy() {
		return new Nonet<>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public Nonet<A,B,C,D,E,F,G,H,I> withA(A a) {
		return new Nonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public Nonet<A,B,C,D,E,F,G,H,I> withB(B b) {
		return new Nonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public Nonet<A,B,C,D,E,F,G,H,I> withC(C c) {
		return new Nonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public Nonet<A,B,C,D,E,F,G,H,I> withD(D d) {
		return new Nonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public Nonet<A,B,C,D,E,F,G,H,I> withE(E e) {
		return new Nonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public Nonet<A,B,C,D,E,F,G,H,I> withF(F f) {
		return new Nonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public Nonet<A,B,C,D,E,F,G,H,I> withG(G g) {
		return new Nonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public Nonet<A,B,C,D,E,F,G,H,I> withH(H h) {
		return new Nonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	public Nonet<A,B,C,D,E,F,G,H,I> withI(I i) {
		return new Nonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}
	
	@Override
	public Octet<A,C,D,E,F,G,H,I> dropB() {
		return new Octet<A,C,D,E,F,G,H,I>(a, c, d, e, f, g, h, i);
	}

	@Override
	public Octet<A,B,D,E,F,G,H,I> dropC() {
		return new Octet<A,B,D,E,F,G,H,I>(a, b, d, e, f, g, h, i);
	}

	@Override
	public Octet<A,B,C,E,F,G,H,I> dropD() {
		return new Octet<A,B,C,E,F,G,H,I>(a, b, c, e, f, g, h, i);
	}

	@Override
	public Octet<A,B,C,D,F,G,H,I> dropE() {
		return new Octet<A,B,C,D,F,G,H,I>(a, b, c, d, f, g, h, i);
	}

	@Override
	public Octet<A,B,C,D,E,G,H,I> dropF() {
		return new Octet<A,B,C,D,E,G,H,I>(a, b, c, d, e, g, h, i);
	}

	@Override
	public Octet<A,B,C,D,E,F,H,I> dropG() {
		return new Octet<A,B,C,D,E,F,H,I>(a, b, c, d, e, f, h, i);
	}

	public Octet<A,B,C,D,E,F,G,I> dropH() {
		return new Octet<A,B,C,D,E,F,G,I>(a, b, c, d, e, f, g, i);
	}

	@Override
	public Object get(int index) {
		switch (index) {
		case 0:
			return a;
		case 1:
		case -8:
			return b;
		case 2:
		case -7:
			return c;
		case 3:
		case -6:
			return d;
		case 4:
		case -5:
			return e;
		case 5:
		case -4:
			return f;
		case 6:
		case -3:
			return g;
		case 7:
		case -2:
			return h;
		case 8:
		case -1:
			return i;
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

	@Override
	public <X> Tuple push(X element) {
		return new Decet<X,A,B,C,D,E,F,G,H,I>(element, a, b, c, d, e, f, g, h, i);
	}

	@Override
	public <X> Tuple add(X element) {
		return new Decet<A,B,C,D,E,F,G,H,I,X>(a, b, c, d, e, f, g, h, i, element);
	}

	@Override
	public Tuple pop() throws IndexOutOfBoundsException {
		return new Octet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	@Override
	public Tuple popLeft() throws IndexOutOfBoundsException {
		return new Octet<B,C,D,E,F,G,H,I>(b, c, d, e, f, g, h, i);
	}

	@Override
	public <X> Tuple insert(int index, X element) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return push(element);
		case 1:
		case -9:
			return new Decet<A,X,B,C,D,E,F,G,H,I>(a, element, b, c, d, e, f, g, h, i);
		case 2:
		case -8:
			return new Decet<A,B,X,C,D,E,F,G,H,I>(a, b, element, c, d, e, f, g, h, i);
		case 3:
		case -7:
			return new Decet<A,B,C,X,D,E,F,G,H,I>(a, b, c, element, d, e, f, g, h, i);
		case 4:
		case -6:
			return new Decet<A,B,C,D,X,E,F,G,H,I>(a, b, c, d, element, e, f, g, h, i);
		case 5:
		case -5:
			return new Decet<A,B,C,D,E,X,F,G,H,I>(a, b, c, d, e, element, f, g, h, i);
		case 6:
		case -4:
			return new Decet<A,B,C,D,E,F,X,G,H,I>(a, b, c, d, e, f, element, g, h, i);
		case 7:
		case -3:
			return new Decet<A,B,C,D,E,F,G,X,H,I>(a, b, c, d, e, f, g, element, h, i);
		case 8:
		case -2:
			return new Decet<A,B,C,D,E,F,G,H,X,I>(a, b, c, d, e, f, g, h, element, i);
		case -1:
		case 9:
			return add(element);
		default:
			return super.insert(index, element);
		}
	}

	@Override
	public Tuple remove(int index) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return new Octet<B,C,D,E,F,G,H,I>(b, c, d, e, f, g, h, i);
		case 1:
		case -8:
			return new Octet<A,C,D,E,F,G,H,I>(a, c, d, e, f, g, h, i);
		case 2:
		case -7:
			return new Octet<A,B,D,E,F,G,H,I>(a, b, d, e, f, g, h, i);
		case 3:
		case -6:
			return new Octet<A,B,C,E,F,G,H,I>(a, b, c, e, f, g, h, i);
		case 4:
		case -5:
			return new Octet<A,B,C,D,F,G,H,I>(a, b, c, d, f, g, h, i);
		case 5:
		case -4:
			return new Octet<A,B,C,D,E,G,H,I>(a, b, c, d, e, g, h, i);
		case 6:
		case -3:
			return new Octet<A,B,C,D,E,F,H,I>(a, b, c, d, e, f, h, i);
		case 7:
		case -2:
			return new Octet<A,B,C,D,E,F,G,I>(a, b, c, d, e, f, g, i);
		case 8:
		case -1:
			return new Octet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

}
