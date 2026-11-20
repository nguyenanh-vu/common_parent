package com.nguyenanhvu.collections.tuple.comparable;

import com.nguyenanhvu.collections.tuple.IComparableTuple;
import com.nguyenanhvu.collections.tuple.impl.Quintet;

public class ComparableQuintet<A extends Comparable<A>,
								B extends Comparable<B>,
								C extends Comparable<C>,
								D extends Comparable<D>,
								E extends Comparable<E>>
extends Quintet<A,B,C,D,E>
implements IComparableTuple {

	public ComparableQuintet(A a, B b, C c, D d, E e) {
		super(a, b, c, d, e);
	}

	public ComparableQuintet(Quintet<A,B,C,D,E> other) {
		super(other.a, other.b, other.c, other.d, other.e);
	}

	@Override
	public ComparableQuintet<A,B,C,D,E> copy() {
		return new ComparableQuintet<>(a, b, c, d, e);
	}

	@Override
	public ComparableQuintet<A,B,C,D,E> withA(A a) {
		return new ComparableQuintet<A,B,C,D,E>(a, b, c, d, e);
	}

	@Override
	public ComparableQuintet<A,B,C,D,E> withB(B b) {
		return new ComparableQuintet<A,B,C,D,E>(a, b, c, d, e);
	}

	@Override
	public ComparableQuintet<A,B,C,D,E> withC(C c) {
		return new ComparableQuintet<A,B,C,D,E>(a, b, c, d, e);
	}

	@Override
	public ComparableQuintet<A,B,C,D,E> withD(D d) {
		return new ComparableQuintet<A,B,C,D,E>(a, b, c, d, e);
	}

	@Override
	public ComparableQuintet<A,B,C,D,E> withE(E e) {
		return new ComparableQuintet<A,B,C,D,E>(a, b, c, d, e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(IComparableTuple o) {
		int res = IComparableTuple.precompare(this, o);
		if (res != 0) {
			return res;
		}
		
		try {
			ComparableQuintet<A,B,C,D,E> cast = (ComparableQuintet<A,B,C,D,E>) o;
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
			res = IComparableTuple.compare(d, cast.d);
			if (res != 0) {
				return res;
			}
			return IComparableTuple.compare(e, cast.e);
		} catch (ClassCastException e) {
			return 0;
		}
	}

}
