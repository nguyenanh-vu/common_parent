package com.nguyenanhvu.collections.tuple.comparable;

import com.nguyenanhvu.collections.tuple.IComparableTuple;
import com.nguyenanhvu.collections.tuple.impl.Quartet;

public class ComparableQuartet<A extends Comparable<A>,
								B extends Comparable<B>,
								C extends Comparable<C>,
								D extends Comparable<D>>
extends Quartet<A,B,C,D>
implements IComparableTuple {

	public ComparableQuartet(A a, B b, C c, D d) {
		super(a, b, c, d);
	}
	
	public ComparableQuartet(Quartet<A,B,C,D> other) {
		super(other.a, other.b, other.c, other.d);
	}

	@Override
	public ComparableQuartet<A,B,C,D> copy() {
		return new ComparableQuartet<>(a, b, c, d);
	}

	@Override
	public ComparableQuartet<A,B,C,D> withA(A a) {
		return new ComparableQuartet<A,B,C,D>(a, b, c, d);
	}

	@Override
	public ComparableQuartet<A,B,C,D> withB(B b) {
		return new ComparableQuartet<A,B,C,D>(a, b, c, d);
	}

	@Override
	public ComparableQuartet<A,B,C,D> withC(C c) {
		return new ComparableQuartet<A,B,C,D>(a, b, c, d);
	}

	@Override
	public ComparableQuartet<A,B,C,D> withD(D d) {
		return new ComparableQuartet<A,B,C,D>(a, b, c, d);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(IComparableTuple o) {
		int res = IComparableTuple.precompare(this, o);
		if (res != 0) {
			return res;
		}
		
		try {
			ComparableQuartet<A,B,C,D> cast = (ComparableQuartet<A,B,C,D>) o;
			res = IComparableTuple.compare(a, cast.a);
			if (res != 0) {
				return res;
			}
			res = IComparableTuple.compare(b, cast.b);
			if (res != 0) {
				return res;
			}
			res = IComparableTuple.compare(c, cast.c);
			if (res != 0) {
				return res;
			}
			return IComparableTuple.compare(d, cast.d);
		} catch (ClassCastException e) {
			return 0;
		}
	}

}
