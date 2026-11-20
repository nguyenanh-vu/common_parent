package com.nguyenanhvu.collections.tuple.comparable;

import com.nguyenanhvu.collections.tuple.IComparableTuple;
import com.nguyenanhvu.collections.tuple.impl.Trio;

public class ComparableTrio<A extends Comparable<A>,
							B extends Comparable<B>,
							C extends Comparable<C>>
extends Trio<A,B,C>
implements IComparableTuple {

	public ComparableTrio(A a, B b, C c) {
		super(a, b, c);
	}
	
	public ComparableTrio(Trio<A,B,C> other) {
		super(other.a, other.b, other.c);
	}

	@Override
	public ComparableTrio<A,B,C> copy() {
		return new ComparableTrio<>(a, b, c);
	}

	@Override
	public ComparableTrio<A,B,C> withA(A a) {
		return new ComparableTrio<A,B,C>(a, b, c);
	}

	@Override
	public ComparableTrio<A,B,C> withB(B b) {
		return new ComparableTrio<A,B,C>(a, b, c);
	}

	@Override
	public ComparableTrio<A,B,C> withC(C c) {
		return new ComparableTrio<A,B,C>(a, b, c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(IComparableTuple o) {
		int res = IComparableTuple.precompare(this, o);
		if (res != 0) {
			return res;
		}
		
		try {
			ComparableTrio<A,B,C> cast = (ComparableTrio<A,B,C>) o;
			res = IComparableTuple.compare(a, cast.a);
			if (res != 0) {
				return res;
			}
			res = IComparableTuple.compare(b, cast.b);
			if (res != 0) {
				return res;
			}
			return IComparableTuple.compare(c, cast.c);
		} catch (ClassCastException e) {
			return 0;
		}
	}
}
