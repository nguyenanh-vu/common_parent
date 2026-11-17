package com.nguyenanhvu.collections.tuple;

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
	public Tuple copy() {
		return new Empty();
	}

	@Override
	public Object get(int index) {
		throw new IndexOutOfBoundsException(Integer.toString(index));
	}

	@Override
	public <X> Tuple push(X element) {
		return new Solo<X>(element);
	}

	@Override
	public <X> Tuple add(X element) {
		return new Solo<X>(element);
	}

	@Override
	public Tuple pop() throws IndexOutOfBoundsException {
		throw new IndexOutOfBoundsException("cannot invoke pop() on Empty tuple");
	}

	@Override
	public Tuple popLeft() throws IndexOutOfBoundsException {
		throw new IndexOutOfBoundsException("cannot invoke popLeft() on Empty tuple");
	}

	@Override
	public <X> Tuple insert(int index, X element) throws IndexOutOfBoundsException {
		if (index != 0) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		return new Solo<X>(element);
	}

	@Override
	public Tuple remove(int index) throws IndexOutOfBoundsException {
		throw new IndexOutOfBoundsException("cannot invoke remove() on Empty tuple");
	}
}
