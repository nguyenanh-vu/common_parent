package com.nguyenanhvu.collections.tuple.comparable;

import com.nguyenanhvu.collections.tuple.IComparableTuple;
import com.nguyenanhvu.collections.tuple.impl.Solo;

public class ComparableSolo<A extends Comparable<A>>
extends Solo<A>
implements IComparableTuple {

	public ComparableSolo(A a) {
		super(a);
	}

	public ComparableSolo(Solo<A> other) {
		super(other.a);
	}

	@Override
	public ComparableSolo<A> copy() {
		return new ComparableSolo<A>(a);
	}

	@Override
	public ComparableSolo<A> withA(A a) {
		return new ComparableSolo<A>(a);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(IComparableTuple o) {
		
		int res = IComparableTuple.precompare(this, o);
		if (res != 0) {
			return res;
		}
		try {
			return IComparableTuple.compare(a, ((ComparableSolo<A>) o).a);
		} catch (ClassCastException e) {
			return 0;
		}
	}

}
