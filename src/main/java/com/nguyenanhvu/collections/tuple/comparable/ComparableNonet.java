package com.nguyenanhvu.collections.tuple.comparable;

import com.nguyenanhvu.collections.tuple.IComparableTuple;
import com.nguyenanhvu.collections.tuple.impl.Nonet;

public class ComparableNonet<A extends Comparable<A>,
								B extends Comparable<B>,
								C extends Comparable<C>,
								D extends Comparable<D>,
								E extends Comparable<E>,
								F extends Comparable<F>,
								G extends Comparable<G>,
								H extends Comparable<H>,
								I extends Comparable<I>>
extends Nonet<A,B,C,D,E,F,G,H,I>
implements IComparableTuple {

	public ComparableNonet(A a, B b, C c, D d, E e, F f, G g, H h, I i) {
		super(a, b, c, d, e, f, g, h, i);
	}

	public ComparableNonet(Nonet<A,B,C,D,E,F,G,H,I> other) {
		super(other.a, other.b, other.c, other.d, other.e, other.f, other.g, other.h, other.i);
	}

	@Override
	public ComparableNonet<A,B,C,D,E,F,G,H,I> copy() {
		return new ComparableNonet<>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public ComparableNonet<A,B,C,D,E,F,G,H,I> withA(A a) {
		return new ComparableNonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public ComparableNonet<A,B,C,D,E,F,G,H,I> withB(B b) {
		return new ComparableNonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public ComparableNonet<A,B,C,D,E,F,G,H,I> withC(C c) {
		return new ComparableNonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public ComparableNonet<A,B,C,D,E,F,G,H,I> withD(D d) {
		return new ComparableNonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public ComparableNonet<A,B,C,D,E,F,G,H,I> withE(E e) {
		return new ComparableNonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public ComparableNonet<A,B,C,D,E,F,G,H,I> withF(F f) {
		return new ComparableNonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public ComparableNonet<A,B,C,D,E,F,G,H,I> withG(G g) {
		return new ComparableNonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public ComparableNonet<A,B,C,D,E,F,G,H,I> withH(H h) {
		return new ComparableNonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@Override
	public ComparableNonet<A,B,C,D,E,F,G,H,I> withI(I i) {
		return new ComparableNonet<A,B,C,D,E,F,G,H,I>(a, b, c, d, e, f, g, h, i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(IComparableTuple o) {
		int res = IComparableTuple.precompare(this, o);
		if (res != 0) {
			return res;
		}
		
		try {
			ComparableNonet<A,B,C,D,E,F,G,H,I> cast = (ComparableNonet<A,B,C,D,E,F,G,H,I>) o;
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
			res = IComparableTuple.compare(g, cast.g);
			if (res != 0) {
				return res;
			}
			res = IComparableTuple.compare(h, cast.h);
			if (res != 0) {
				return res;
			}
			return IComparableTuple.compare(i, cast.i);
		} catch (ClassCastException e) {
			return 0;
		}
	}

}
