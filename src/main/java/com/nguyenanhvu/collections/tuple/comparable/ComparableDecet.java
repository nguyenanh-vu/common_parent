package com.nguyenanhvu.collections.tuple.comparable;

import com.nguyenanhvu.collections.tuple.IComparableTuple;
import com.nguyenanhvu.collections.tuple.impl.Decet;

public class ComparableDecet<A extends Comparable<A>,
								B extends Comparable<B>,
								C extends Comparable<C>,
								D extends Comparable<D>,
								E extends Comparable<E>,
								F extends Comparable<F>,
								G extends Comparable<G>,
								H extends Comparable<H>,
								I extends Comparable<I>,
								J extends Comparable<J>>
extends Decet<A,B,C,D,E,F,G,H,I,J>
implements IComparableTuple {

	public ComparableDecet(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j) {
		super(a, b, c, d, e, f, g, h, i, j);
	}

	public ComparableDecet(Decet<A,B,C,D,E,F,G,H,I,J> other) {
		super(other.a, other.b, other.c, other.d, other.e, other.f, other.g, other.h, other.i, other.j);
	}

	@Override
	public ComparableDecet<A,B,C,D,E,F,G,H,I,J> copy() {
		return new ComparableDecet<>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public ComparableDecet<A,B,C,D,E,F,G,H,I,J> withA(A a) {
		return new ComparableDecet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public ComparableDecet<A,B,C,D,E,F,G,H,I,J> withB(B b) {
		return new ComparableDecet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public ComparableDecet<A,B,C,D,E,F,G,H,I,J> withC(C c) {
		return new ComparableDecet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public ComparableDecet<A,B,C,D,E,F,G,H,I,J> withD(D d) {
		return new ComparableDecet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public ComparableDecet<A,B,C,D,E,F,G,H,I,J> withE(E e) {
		return new ComparableDecet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public ComparableDecet<A,B,C,D,E,F,G,H,I,J> withF(F f) {
		return new ComparableDecet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public ComparableDecet<A,B,C,D,E,F,G,H,I,J> withG(G g) {
		return new ComparableDecet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public ComparableDecet<A,B,C,D,E,F,G,H,I,J> withH(H h) {
		return new ComparableDecet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public ComparableDecet<A,B,C,D,E,F,G,H,I,J> withI(I i) {
		return new ComparableDecet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@Override
	public ComparableDecet<A,B,C,D,E,F,G,H,I,J> withJ(J j) {
		return new ComparableDecet<A,B,C,D,E,F,G,H,I,J>(a, b, c, d, e, f, g, h, i, j);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(IComparableTuple o) {
		int res = IComparableTuple.precompare(this, o);
		if (res != 0) {
			return res;
		}
		
		try {
			ComparableDecet<A,B,C,D,E,F,G,H,I,J> cast = (ComparableDecet<A,B,C,D,E,F,G,H,I,J>) o;
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
			res = IComparableTuple.compare(i, cast.i);
			if (res != 0) {
				return res;
			}
			return IComparableTuple.compare(j, cast.j);
		} catch (ClassCastException e) {
			return 0;
		}
	}

}
