package com.nguyenanhvu.collections.tuple.impl;

public class Decet<A,B,C,D,E,F,G,H,I,J> extends Nonet<A,B,C,D,E,F,G,H,I> {
	
	public final J j;

	public Decet(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j) {
		super(a, b, c, d, e, f, g, h, i);
		this.j = j;
	}

	@Override
	public int size() {
		return 10;
	}

	@Override
	public Object[] toArray() {
		return new Object[]{a, b, c, d, e, f, g, h, i, j};
	}

	@Override
	public Decet<A,B,C,D,E,F,G,H,I,J> copy() {
		return new Decet<>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public Decet<A,B,C,D,E,F,G,H,I,J> withA(A a) {
		return new Decet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public Decet<A,B,C,D,E,F,G,H,I,J> withB(B b) {
		return new Decet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public Decet<A,B,C,D,E,F,G,H,I,J> withC(C c) {
		return new Decet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public Decet<A,B,C,D,E,F,G,H,I,J> withD(D d) {
		return new Decet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public Decet<A,B,C,D,E,F,G,H,I,J> withE(E e) {
		return new Decet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public Decet<A,B,C,D,E,F,G,H,I,J> withF(F f) {
		return new Decet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public Decet<A,B,C,D,E,F,G,H,I,J> withG(G g) {
		return new Decet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public Decet<A,B,C,D,E,F,G,H,I,J> withH(H h) {
		return new Decet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public Decet<A,B,C,D,E,F,G,H,I,J> withI(I i) {
		return new Decet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	public Decet<A,B,C,D,E,F,G,H,I,J> withJ(J j) {
		return new Decet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public Nonet<A,C,D,E,F,G,H,I,J> dropB() {
		return new Nonet<A,C,D,E,F,G,H,I,J>(a, c, d, e, f, g, h, i, j);
	}

	@Override
	public Nonet<A,B,D,E,F,G,H,I,J> dropC() {
		return new Nonet<A,B,D,E,F,G,H,I,J>(a, b, d, e, f, g, h, i, j);
	}

	@Override
	public Nonet<A,B,C,E,F,G,H,I,J> dropD() {
		return new Nonet<A,B,C,E,F,G,H,I,J>(a, b, c, e, f, g, h, i, j);
	}

	@Override
	public Nonet<A,B,C,D,F,G,H,I,J> dropE() {
		return new Nonet<A,B,C,D,F,G,H,I,J>(a, b, c, d, f, g, h, i, j);
	}

	@Override
	public Nonet<A,B,C,D,E,G,H,I,J> dropF() {
		return new Nonet<A,B,C,D,E,G,H,I,J>(a, b, c, d, e, g, h, i, j);
	}

	@Override
	public Nonet<A,B,C,D,E,F,H,I,J> dropG() {
		return new Nonet<A,B,C,D,E,F,H,I,J>(a, b, c, d, e, f, h, i, j);
	}

	@Override
	public Nonet<A,B,C,D,E,F,G,I,J> dropH() {
		return new Nonet<A,B,C,D,E,F,G,I,J>(a, b, c, d, e, f, g, i, j);
	}

	public Nonet<A,B,C,D,E,F,G,H,J> dropI() {
		return new Nonet<A,B,C,D,E,F,G,H,J>(a, b, c, d, e, f, g, h, j);
	}

	@Override
	public Object get(int index) {
		switch (index) {
		case 0:
			return a;
		case 1:
		case -9:
			return b;
		case 2:
		case -8:
			return c;
		case 3:
		case -7:
			return d;
		case 4:
		case -6:
			return e;
		case 5:
		case -5:
			return f;
		case 6:
		case -4:
			return g;
		case 7:
		case -3:
			return h;
		case 8:
		case -2:
			return i;
		case 9:
		case -1:
			return j;
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

	@Override
	public <X> Decet<?,?,?,?,?,?,?,?,?,?> push(X element) {
		throw new IndexOutOfBoundsException("no push() in Decet");
	}

	@Override
	public <X> Decet<?,?,?,?,?,?,?,?,?,?> add(X element) {
		throw new IndexOutOfBoundsException("no add() in Decet");
	}

	@Override
	public Nonet<?,?,?,?,?,?,?,?,?> pop() throws IndexOutOfBoundsException {
		return new Nonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public Nonet<?,?,?,?,?,?,?,?,?> popLeft() throws IndexOutOfBoundsException {
		return new Nonet<B,C,D,E,F,G,H,I,J>(b, c, d, e, f, g, h, i, j);
	}

	@Override
	public <X> Decet<?,?,?,?,?,?,?,?,?,?> insert(int index, X element) throws IndexOutOfBoundsException {
		throw new IndexOutOfBoundsException("no insert() in Decet");
	}

	@Override
	public Nonet<?,?,?,?,?,?,?,?,?> remove(int index) throws IndexOutOfBoundsException {
		switch (index) {
		case 0:
			return new Nonet<B,C,D,E,F,G,H,I,J>(b, c, d, e, f, g, h, i, j);
		case 1:
		case -9:
			return new Nonet<A,C,D,E,F,G,H,I,J>(a, c, d, e, f, g, h, i, j);
		case 2:
		case -8:
			return new Nonet<A,B,D,E,F,G,H,I,J>(a, b, d, e, f, g, h, i, j);
		case 3:
		case -7:
			return new Nonet<A,B,C,E,F,G,H,I,J>(a, b, c, e, f, g, h, i, j);
		case 4:
		case -6:
			return new Nonet<A,B,C,D,F,G,H,I,J>(a, b, c, d, f, g, h, i, j);
		case 5:
		case -5:
			return new Nonet<A,B,C,D,E,G,H,I,J>(a, b, c, d, e, g, h, i, j);
		case 6:
		case -4:
			return new Nonet<A,B,C,D,E,F,H,I,J>(a, b, c, d, e, f, h, i, j);
		case 7:
		case -3:
			return new Nonet<A,B,C,D,E,F,G,I,J>(a, b, c, d, e, f, g, i, j);
		case 8:
		case -2:
			return new Nonet<A,B,C,D,E,F,G,H,J>(a, b, c, d, e, f, g, h, j);
		case 9:
		case -1:
			return new Nonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
		default:
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

}
