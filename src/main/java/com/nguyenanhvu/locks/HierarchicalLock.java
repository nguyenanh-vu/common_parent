package com.nguyenanhvu.locks;

import java.util.HashMap;
import java.util.Map;

public class HierarchicalLock<T> {
	
	private Object lock = new Object();
	private Map<T, Object> keys = new HashMap<>();
	
	public Object lock() throws InterruptedException {
		if (!keys.isEmpty()) {
			lock.wait();
		}
		return lock;
	}
	
	public Object lock(T key) {
		synchronized(lock) {
			if (!keys.containsKey(key)) {
				keys.put(key, new Object());
			}
			return keys.get(key);
		}
	}
	
	public void unlock(T key) {
		synchronized(lock) {
			keys.remove(key);
			lock.notifyAll();
		}
	}
	
	public void doWithLock(Runnable runnable) throws InterruptedException {
		synchronized (this.lock()) {
			runnable.run();
		}
	}
	
	public void doWithLock(T key, Runnable runnable) {
		try {
			synchronized (this.lock(key)) {
				runnable.run();
			}
		} finally {
			this.unlock(key);
		}
	}
}
