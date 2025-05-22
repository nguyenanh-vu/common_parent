package com.nguyenanhvu.collections.tuple;

public class Trio<A, B, C> extends Duo<A, B> {
	
	public final C c;
	
	public Trio(A a, B b, C c) {
		super(a, b);
		this.c = c;
	}

	@Override
	public int getSize() {
		return 3;
	}

	@Override
	public Object[] getElements() {
		return new Object[]{a, b, c};
	}

	@Override
	public Trio<A, B, C> withA(A a) {
		return new Trio<A, B, C>(a, b, c);
	}

	@Override
	public Trio<A, B, C> withB(B b) {
		return new Trio<A, B, C>(a, b, c);
	}
	
	public Trio<A, B, C> withC(C c) {
		return new Trio<A, B, C>(a, b, c);
	}
}