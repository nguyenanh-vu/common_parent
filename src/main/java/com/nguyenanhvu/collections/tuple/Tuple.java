package com.nguyenanhvu.collections.tuple;

public abstract class Tuple {
	
	public abstract int getSize();
	
	public abstract Object[] getElements();
	
	@Override
	public int hashCode() {
		int res = 7;
		for (Object o : getElements()) {
			res = res * 31 + o.hashCode();
		}
		return res;
	}
	
	@Override
	public boolean equals(Object o) {
		if ( !(o instanceof Tuple)) {
			return false;
		}
		
		Tuple asTuple = (Tuple) o;
		if (asTuple.getSize() != this.getSize()) {
			return false;
		}
		
		Object[] e1 = this.getElements();
		Object[] e2 = asTuple.getElements();
		
		for (int i = 0; i < this.getSize(); i++) {
			if (!e1[i].equals(e2[i])) {
				return false;
			}
		}
		return true;
	}
}