package com.nguyenanhvu.collections.tuple.comparable;

import com.nguyenanhvu.collections.tuple.IComparableTuple;
import com.nguyenanhvu.collections.tuple.impl.Octet;

public class ComparableOctet<A extends Comparable<A>,
								B extends Comparable<B>,
								C extends Comparable<C>,
								D extends Comparable<D>,
								E extends Comparable<E>,
								F extends Comparable<F>,
								G extends Comparable<G>,
								H extends Comparable<H>>
extends Octet<A,B,C,D,E,F,G,H>
implements IComparableTuple {

	public ComparableOctet(A a, B b, C c, D d, E e, F f, G g, H h) {
		super(a, b, c, d, e, f, g, h);
	}

	public ComparableOctet(Octet<A,B,C,D,E,F,G,H> other) {
		super(other.a, other.b, other.c, other.d, other.e, other.f, other.g, other.h);
	}

	@Override
	public ComparableOctet<A,B,C,D,E,F,G,H> copy() {
		return new ComparableOctet<>(a, b, c, d, e, f, g, h);
	}

	@Override
	public ComparableOctet<A,B,C,D,E,F,G,H> withA(A a) {
		return new ComparableOctet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	@Override
	public ComparableOctet<A,B,C,D,E,F,G,H> withB(B b) {
		return new ComparableOctet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	@Override
	public ComparableOctet<A,B,C,D,E,F,G,H> withC(C c) {
		return new ComparableOctet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	@Override
	public ComparableOctet<A,B,C,D,E,F,G,H> withD(D d) {
		return new ComparableOctet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	@Override
	public ComparableOctet<A,B,C,D,E,F,G,H> withE(E e) {
		return new ComparableOctet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	@Override
	public ComparableOctet<A,B,C,D,E,F,G,H> withF(F f) {
		return new ComparableOctet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	@Override
	public ComparableOctet<A,B,C,D,E,F,G,H> withG(G g) {
		return new ComparableOctet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	@Override
	public ComparableOctet<A,B,C,D,E,F,G,H> withH(H h) {
		return new ComparableOctet<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(IComparableTuple o) {
		int res = IComparableTuple.precompare(this, o);
		if (res != 0) {
			return res;
		}
		
		try {
			ComparableOctet<A,B,C,D,E,F,G,H> cast = (ComparableOctet<A,B,C,D,E,F,G,H>) o;
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
			return IComparableTuple.compare(h, cast.h);
		} catch (ClassCastException e) {
			return 0;
		}
	}

}
