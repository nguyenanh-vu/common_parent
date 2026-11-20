package com.nguyenanhvu.collections.tuple.comparable;

import com.nguyenanhvu.collections.tuple.IComparableTuple;
import com.nguyenanhvu.collections.tuple.impl.Septet;

public class ComparableSeptet<A extends Comparable<A>,
								B extends Comparable<B>,
								C extends Comparable<C>,
								D extends Comparable<D>,
								E extends Comparable<E>,
								F extends Comparable<F>,
								G extends Comparable<G>>
extends Septet<A,B,C,D,E,F,G>
implements IComparableTuple {

	public ComparableSeptet(A a, B b, C c, D d, E e, F f, G g) {
		super(a, b, c, d, e, f, g);
	}

	public ComparableSeptet(Septet<A,B,C,D,E,F,G> other) {
		super(other.a, other.b, other.c, other.d, other.e, other.f, other.g);
	}

	@Override
	public ComparableSeptet<A,B,C,D,E,F,G> copy() {
		return new ComparableSeptet<>(a, b, c, d, e, f, g);
	}

	@Override
	public ComparableSeptet<A,B,C,D,E,F,G> withA(A a) {
		return new ComparableSeptet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
	}

	@Override
	public ComparableSeptet<A,B,C,D,E,F,G> withB(B b) {
		return new ComparableSeptet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
	}

	@Override
	public ComparableSeptet<A,B,C,D,E,F,G> withC(C c) {
		return new ComparableSeptet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
	}

	@Override
	public ComparableSeptet<A,B,C,D,E,F,G> withD(D d) {
		return new ComparableSeptet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
	}

	@Override
	public ComparableSeptet<A,B,C,D,E,F,G> withE(E e) {
		return new ComparableSeptet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
	}

	@Override
	public ComparableSeptet<A,B,C,D,E,F,G> withF(F f) {
		return new ComparableSeptet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
	}

	@Override
	public ComparableSeptet<A,B,C,D,E,F,G> withG(G g) {
		return new ComparableSeptet<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(IComparableTuple o) {
		int res = IComparableTuple.precompare(this, o);
		if (res != 0) {
			return res;
		}
		
		try {
			ComparableSeptet<A,B,C,D,E,F,G> cast = (ComparableSeptet<A,B,C,D,E,F,G>) o;
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
			res = IComparableTuple.compare(f, cast.f);
			if (res != 0) {
				return res;
			}
			return IComparableTuple.compare(g, cast.g);
		} catch (ClassCastException e) {
			return 0;
		}
	}

}
