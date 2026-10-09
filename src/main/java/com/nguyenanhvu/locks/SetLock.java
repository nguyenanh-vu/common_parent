package com.nguyenanhvu.locks;

import java.util.HashSet;
import java.util.Set;

public class SetLock<T> {
	
	private Set<T> set = new HashSet<>();
	private Object lock = new Object();
	
	public void add(T t) {
		set.add(t);
	}
	
	public boolean remove(T t) {
		synchronized (lock) {
			boolean res = set.remove(t);
			if (res && set.isEmpty()) {
				lock.notifyAll();
			}
			return res;
		}
	}
	
	public void clear() {
		synchronized (lock) {
			set.clear();
			lock.notifyAll();
		}
	}
	
	public void await() throws InterruptedException {
		if (!set.isEmpty()) {
			lock.wait();
		}
	}
	
}
