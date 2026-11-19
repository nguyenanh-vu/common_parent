package com.nguyenanhvu.collections;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import lombok.NonNull;

public class ArrayLinkedList<T> implements List<T> {
	
	private static int DEFAULT_BLOCK_SIZE = 8;
	
	public static class Node<T> {
		
		private final T[] content;
		private final boolean[] filter;
		private final int capacity;
		public Node<T> previous = null;
		public Node<T> next = null; 
		private int size = 0;
		private int availableCapacity;
		
		@SuppressWarnings("unchecked")
		public Node(int capacity) {
			this.capacity = capacity;
			this.availableCapacity = capacity;
			content = (T[]) new Object[capacity];
			filter = new boolean[capacity];
		}
		
		public T get(int index, boolean withSkip) {
			return get(withSkip? getIndexWithSkip(index) : index);
		}
		
		public boolean append(T o) {
			int index = getIndexWithSkip(size);
			if (index >= capacity) {
				return false;
			}
			T prev = set(index, o);
			if (prev != null) {
				return false;
			}
			size++;
			return true;
		}
		
		public boolean delete(int index) throws ArrayIndexOutOfBoundsException {
			if (index < 0 || index >= size) {
				throw new ArrayIndexOutOfBoundsException(Integer.toString(index));
			}
			int pos = getIndexWithSkip(index);
			if (pos >= capacity) {
				return false;
			}
			T prev = set(pos, null);
			if (prev == null) {
				return false;
			}
			size--;
			availableCapacity--;
			filter[pos] = true;
			return true;
		}
		
		public T modify(int index, T o) throws ArrayIndexOutOfBoundsException {
			if (index < 0 || index >= size) {
				throw new ArrayIndexOutOfBoundsException(Integer.toString(index));
			}
			return set(getIndexWithSkip(index), o);
		}
		
		public boolean contains(T o) {
			int checked = 0;
			for (int i = 0; i < capacity; i++) {
				if (checked >= size) {
					return false;
				}
				if (!filter[i]) {
					checked++;
					if (Objects.equals(o, content[i])) {
						return true;
					}
				}
			}
			
			return false;
		}
		
		@SuppressWarnings("unchecked")
		public T[] toArray(T[] a, int offset) {
			T[] res = a;
			if (offset + size > a.length) {
				res = (T[]) (new Object[offset + size]);
				System.arraycopy(a, 0, res, 0, offset);
			}
			for (int i = 0; i < size; i++) {
				res[offset + i] = content[getIndexWithSkip(i)];
			}
			return res;
		}
		
		private T get(int i) throws ArrayIndexOutOfBoundsException {
			if (i < 0 || i >= capacity) {
				throw new ArrayIndexOutOfBoundsException(Integer.toString(i));
			}
			return content[i];
		}
		
		private T set(int i, T o) throws ArrayIndexOutOfBoundsException {
			if (i < 0 || i >= capacity) {
				throw new ArrayIndexOutOfBoundsException(Integer.toString(i));
			}
			
			T prev = content[i];
			content[i] = o;
			return prev;
		}
		
		private int getIndexWithSkip(int index) {
			if (index < 0 || index >= availableCapacity) {
				throw new ArrayIndexOutOfBoundsException(Integer.toString(index));
			}
			int count = 0;
			int res = 0;
			while (count < index || filter[res]) {
				res++;
				if (!filter[res]) {
					count++;
				}
			}
			return res;
		}
		
		public int size() {
			return size;
		}	
	}
	
	public static class NodeIterator<T> implements ListIterator<Node<T>> {
		
		@NonNull
		private Node<T> current;
		@NonNull
		private final ArrayLinkedList<T> list;
		private int index = 0;
		private Node<T> last= null;
		
		public NodeIterator(ArrayLinkedList<T> list) {
			current = list.head;
			this.list = list;
		}
		
		@Override
		public void add(Node<T> e) {
			e.previous = current.previous;
			e.next = current;
			if (current == null) {
				list.tail = e;
			} else {
				current.previous = e;
			}
			if (current.previous == null) {
				list.head = e;
			} else {
				current.previous.next = e;
			}
			current = e;
			last = null;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public boolean hasPrevious() {
			return current.previous != null;
		}

		@Override
		public Node<T> next() {
			if (current == null) {
				throw new NoSuchElementException();
			}
			Node<T> res = current;
			current = current.next;
			index++;
			last = res;
			return res;
		}

		@Override
		public int nextIndex() {
			return index;
		}

		@Override
		public Node<T> previous() {
			if (current.previous == null) {
				throw new NoSuchElementException();
			}
			current = current.previous;
			index--;
			last = current;
			return current;
		}

		@Override
		public int previousIndex() {
			return index - 1;
		}

		@Override
		public void remove() {
			if (last == null  || (last.next == null && last.previous == null)) {
				throw new IllegalStateException();
			}
			if (current != last) {
				index--;
			}
			if (last.next == null) {
				list.tail = last.previous;
			} else {
				last.next.previous = last.previous;
			}
			if (last.previous == null) {
				list.head = last.next;
			} else {
				last.previous.next = last.previous;
			}
			last = null;
		}

		@Override
		public void set(Node<T> e) {
			if (last == null) {
				throw new IllegalStateException();
			}
			e.previous = last.previous;
			e.next = last.next;
			if (last.next == null) {
				list.tail = e;
			} else {
				e.next.previous = e;
			}
			if (last.previous == null) {
				list.head = e;
			} else {
				e.previous.next = e;
			}
			last = e;
		}
		
	}
	
	public static class Iterator<T> implements ListIterator<T> {

		@Override
		public void add(T e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public T previous() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void set(T e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private final int blocksize;
	private Node<T> head;
	private Node<T> tail;
	private int nodeCount = 0;
	
	public ArrayLinkedList() {
		this(DEFAULT_BLOCK_SIZE);
	}
	
	public ArrayLinkedList(int blocksize) {
		this.blocksize = blocksize;
		Node<T> initialBlock = getNewNode();
		head = initialBlock;
		tail = initialBlock;
	}
	
	// node management
	
	private Node<T> getNewNode() {
		return new Node<T>(blocksize);
	}
	
	private boolean addNodeTail() {
		Node<T> newNode = getNewNode();
		newNode.previous = tail;
		tail.next = newNode;
		tail = newNode;
		nodeCount++;
		return true;
	}

	private boolean addNodeHead() {
		Node<T> newNode = getNewNode();
		newNode.next = head;
		head.previous = newNode;
		head = newNode;
		nodeCount++;
		return true;
	}

	private boolean addNode(int pos) {
		Node<T> current;
		int count = 0;
		Node<T> newNode = getNewNode();
		if (pos == 0) {
			return addNodeHead();
		} else if (pos == -1) {
			return addNodeTail();
		} else if (pos > 0) {
			current = head;
			while (current != null && count < pos - 1) {
				current = current.next;
				count++;
			}
			if (current == null) {
				return false;
			}
			newNode.previous = current;
			newNode.next = current.next;
			current.next = newNode;
			if (newNode.next != null) {
				newNode.next.previous = newNode;
			} else {
				tail = newNode;
			}
			nodeCount++;
			return true;
		} else {
			current = tail;
			while (current != null && count > pos + 2) {
				current = current.previous;
				count--;
			}
			if (current == null) {
				return false;
			}
			newNode.previous= current;
			newNode.next = current.previous;
			current.previous = newNode;
			if (newNode.previous != null) {
				newNode.previous.next= newNode;
			} else {
				head = newNode;
			}
			nodeCount++;
			return true;
		}
	}
	
	private boolean removeNodeHead() {
		if (head.next == null) {
			return false;
		}
		head.next.previous = null;
		head = head.next;
		nodeCount--;
		return true;
	}
	
	private boolean removeNodeTail() {
		if (tail.previous== null) {
			return false;
		}
		tail.previous.next = null;
		tail = tail.previous;
		nodeCount--;
		return true;
	}
	
	private boolean removeNode(int pos) {
		Node<T> current;
		int count = 0;
		if (pos == 0) {
			return removeNodeHead();
		} else if (pos == -1) {
			return removeNodeTail();
		} else if (pos > 0) {
			current = head;
			while (current != null && count < pos - 1) {
				current = current.next;
				count++;
			}
			if (current == null || current.next == null) {
				return false;
			}
			
			Node<T> toDelete = current.next;
			current.next = toDelete.next;
			if (toDelete.next == null) {
				tail = current;
			} else {
				toDelete.next.previous = current;
			}
			nodeCount--;
			return true;
		} else {
			current = tail;
			while (current != null && count > pos + 2) {
				current = current.previous;
				count--;
			}
			if (current == null || current.previous == null) {
				return false;
			}
			
			Node<T> toDelete = current.previous;
			current.previous = toDelete.previous;
			if (toDelete.previous == null) {
				head = current;
			} else {
				toDelete.previous.next = current;
			}
			nodeCount--;
			return true;
		}
	}
	
	protected ListIterator<Node<T>> nodeIterator() {
		return new NodeIterator<T>(this);
	}
	
	
	// implements List

	@Override
	public int size() {
		int res = 0;
		ListIterator<Node<T>> it = nodeIterator();
		while (it.hasNext()) {
			res += it.next().size;
		}
		return res;
	}

	@Override
	public boolean isEmpty() {
		return nodeCount == 1 && head.size == 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object o) {
		
		T cast;
		try {
			cast = (T) o;
		} catch (Exception e) {
			return false;
		}
		
		ListIterator<Node<T>> it = nodeIterator();
		while (it.hasNext()) {
			if (it.next().contains(cast)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T set(int index, T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, T element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
