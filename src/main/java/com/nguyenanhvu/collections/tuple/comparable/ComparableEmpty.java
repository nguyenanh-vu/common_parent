package com.nguyenanhvu.collections.tuple.comparable;

import com.nguyenanhvu.collections.tuple.IComparableTuple;
import com.nguyenanhvu.collections.tuple.impl.Empty;

public class ComparableEmpty
extends Empty
implements IComparableTuple {
	
	public ComparableEmpty () {
		super();
	}

	@Override
	public int compareTo(IComparableTuple o) {
		return o instanceof ComparableEmpty ? 0 : 1;
	}
}
