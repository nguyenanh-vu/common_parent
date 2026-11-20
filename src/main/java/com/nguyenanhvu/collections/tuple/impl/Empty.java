package com.nguyenanhvu.collections.tuple.impl;

import com.nguyenanhvu.collections.tuple.ITuple;

public class Empty extends Tuple {

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Object[] toArray() {
		return new Object[0];
	}

	@Override
	public ITuple copy() {
		return new Empty();
	}

	@Override
	public Object get(int index) {
		throw new IndexOutOfBoundsException(Integer.toString(index));
	}

	@Override
	public <X> Solo<?> push(X element) {
		return new Solo<X>(element);
	}

	@Override
	public <X> Solo<?> add(X element) {
		return new Solo<X>(element);
	}

	@Override
	public ITuple pop() throws IndexOutOfBoundsException {
		throw new IndexOutOfBoundsException("cannot invoke pop() on Empty tuple");
	}

	@Override
	public ITuple popLeft() throws IndexOutOfBoundsException {
		throw new IndexOutOfBoundsException("cannot invoke popLeft() on Empty tuple");
	}

	@Override
	public <X> Solo<?> insert(int index, X element) throws IndexOutOfBoundsException {
		if (index != 0) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		return new Solo<X>(element);
	}

	@Override
	public ITuple remove(int index) throws IndexOutOfBoundsException {
		throw new IndexOutOfBoundsException("cannot invoke remove() on Empty tuple");
	}
}
