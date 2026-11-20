package com.nguyenanhvu.collections.tuple.comparable;

import com.nguyenanhvu.collections.tuple.IComparableTuple;
import com.nguyenanhvu.collections.tuple.impl.Sextet;

public class ComparableSextet<A extends Comparable<A>,
								B extends Comparable<B>,
								C extends Comparable<C>,
								D extends Comparable<D>,
								E extends Comparable<E>,
								F extends Comparable<F>>
extends Sextet<A,B,C,D,E,F>
implements IComparableTuple {

	public ComparableSextet(A a, B b, C c, D d, E e, F f) {
		super(a, b, c, d, e, f);
	}

	public ComparableSextet(Sextet<A,B,C,D,E,F> other) {
		super(other.a, other.b, other.c, other.d, other.e, other.f);
	}

	@Override
	public ComparableSextet<A,B,C,D,E,F> copy() {
		return new ComparableSextet<>(a, b, c, d, e, f);
	}

	@Override
	public ComparableSextet<A,B,C,D,E,F> withA(A a) {
		return new ComparableSextet<A,B,C,D,E,F>(a, b, c, d, e, f);
	}

	@Override
	public ComparableSextet<A,B,C,D,E,F> withB(B b) {
		return new ComparableSextet<A,B,C,D,E,F>(a, b, c, d, e, f);
	}

	@Override
	public ComparableSextet<A,B,C,D,E,F> withC(C c) {
		return new ComparableSextet<A,B,C,D,E,F>(a, b, c, d, e, f);
	}

	@Override
	public ComparableSextet<A,B,C,D,E,F> withD(D d) {
		return new ComparableSextet<A,B,C,D,E,F>(a, b, c, d, e, f);
	}

	@Override
	public ComparableSextet<A,B,C,D,E,F> withE(E e) {
		return new ComparableSextet<A,B,C,D,E,F>(a, b, c, d, e, f);
	}

	@Override
	public ComparableSextet<A,B,C,D,E,F> withF(F f) {
		return new ComparableSextet<A,B,C,D,E,F>(a, b, c, d, e, f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(IComparableTuple o) {
		int res = IComparableTuple.precompare(this, o);
		if (res != 0) {
			return res;
		}
		
		try {
			ComparableSextet<A,B,C,D,E,F> cast = (ComparableSextet<A,B,C,D,E,F>) o;
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
			res = IComparableTuple.compare(e, cast.e);
			if (res != 0) {
				return res;
			}
			return IComparableTuple.compare(f, cast.f);
		} catch (ClassCastException e) {
			return 0;
		}
	}

}
