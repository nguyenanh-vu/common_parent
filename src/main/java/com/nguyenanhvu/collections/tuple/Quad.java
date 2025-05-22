package com.nguyenanhvu.collections.tuple;

public class Quad<A, B, C, D> extends Trio<A, B, C> {
	
	public final D d;
	
	public Quad(A a, B b, C c, D d) {
		super(a, b, c);
		this.d = d;
	}

	@Override
	public int getSize() {
		return 4;
	}

	@Override
	public Object[] getElements() {
		return new Object[]{a, b, c};
	}

	@Override
	public Quad<A, B, C, D> withA(A a) {
		return new Quad<A, B, C, D>(a, b, c, d);
	}

	@Override
	public Quad<A, B, C, D> withB(B b) {
		return new Quad<A, B, C, D>(a, b, c, d);
	}

	@Override
	public Quad<A, B, C, D> withC(C c) {
		return new Quad<A, B, C, D>(a, b, c, d);
	}
	
	public Quad<A, B, C, D> withD(D d) {
		return new Quad<A, B, C, D>(a, b, c, d);
	}

}
