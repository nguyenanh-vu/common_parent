package com.nguyenanhvu.collections.tuple.comparable;

import com.nguyenanhvu.collections.tuple.IComparableTuple;
import com.nguyenanhvu.collections.tuple.impl.Duet;

public class ComparableDuet<A extends Comparable<A>,
							B extends Comparable<B>>
extends Duet<A,B>
implements IComparableTuple {

	public ComparableDuet(A a, B b) {
		super(a, b);
	}
	
	public ComparableDuet(Duet<A,B> other) {
		super(other.a, other.b);
	}

	@Override
	public ComparableDuet<A,B> copy() {
		return new ComparableDuet<>(a, b);
	}
	
	@Override
	public ComparableDuet<A,B> withA(A a) {
		return new ComparableDuet<A,B>(a, b);
	}

	@Override
	public ComparableDuet<A,B> withB(B b) {
		return new ComparableDuet<A,B>(a, b);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(IComparableTuple o) {
		
		int res = IComparableTuple.precompare(this, o);
		if (res != 0) {
			return res;
		}
		
		try {
			ComparableDuet<A,B> cast = (ComparableDuet<A,B>) o;
			res = IComparableTuple.compare(a, cast.a);
			if (res != 0) {
				return res;
			}
			return IComparableTuple.compare(b, cast.b);
		} catch (ClassCastException e) {
			return 0;
		}
	}

}
