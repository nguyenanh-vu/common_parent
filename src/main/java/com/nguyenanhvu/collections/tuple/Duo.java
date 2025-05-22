package com.nguyenanhvu.collections.tuple;

public class Duo<A, B> extends Solo<A> {
	
	public final B b;
	
	public Duo(A a, B b) {
		super(a);
		this.b = b;
	}

	@Override
	public int getSize() {
		return 2;
	}

	@Override
	public Object[] getElements() {
		return new Object[]{a, b};
	}
	
	public Duo<A, B> withA(A a) {
		return new Duo<A, B>(a, b);
	}
	
	public Duo<A, B> withB(B b) {
		return new Duo<A, B>(a, b);
	}
}
