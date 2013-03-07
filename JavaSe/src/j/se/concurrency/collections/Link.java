package j.se.concurrency.collections;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

public class Link {
	public static void main(String[] args) {
		LinkedBlockingQueue queue = new LinkedBlockingQueue();
		ConcurrentLinkedQueue queue1 = new ConcurrentLinkedQueue();
		//queue1.
		AtomicReference ar = new AtomicReference();
		//ar.compareAndSet(expect, update);
		//ar.
		CopyOnWriteArrayList list = new CopyOnWriteArrayList();
		String str = new String("123");
		list.addIfAbsent(str);
		list.addIfAbsent(str);//重复的加不进去
		System.err.println(list.size());
		CopyOnWriteArrayList list1 = new CopyOnWriteArrayList();
		list1.add(str);
		list1.add(str);
		System.err.println(list1.size());
	}
}
