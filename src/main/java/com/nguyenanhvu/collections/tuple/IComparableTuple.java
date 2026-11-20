package com.nguyenanhvu.collections.tuple;

public interface IComparableTuple extends ITuple, Comparable<IComparableTuple> {
	
	public static <T extends Comparable<T>> int compare(T o1, T o2) {
		if (o1 == null) {
			return o2 == null ? 0 :- 1;
		} else if (o2 == null) {
			return 1;
		} else {
			return o1.compareTo(o2);
		}
	}

	public static int precompare(IComparableTuple o1, IComparableTuple o2) {
		int res = Integer.compare(o1.size(), o2.size());
		if (res != 0) {
			return res;
		}
		for (int i = 0; i < o1.size(); i++) {
			Object e1 = o1.get(i);
			Object e2 = o1.get(i);
			if (e1 == null) {
				if (e2 != null) {
					return -1;
				}
			} else if (e2 == null) {
				return 1;
			}
		}
		return 0;
	}
}
