package j.se.concurrency.collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BusinessImpl<A, V> implements IBusiness<A, V> {

	@Override
	public V doWork(A a) {
		System.out.println(Thread.currentThread().getName() + " " + a);
		return null;
	}

	@Override
	public BlockingQueue<A> makeData(int cpacity) {
		BlockingQueue<A> queue = new ArrayBlockingQueue<A>(cpacity);
		for (int i = 0; i < 10000; i++) {
			final A log = (A) ("" + (i + 1));
			try {
				queue.put(log);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return queue;
	}
}
