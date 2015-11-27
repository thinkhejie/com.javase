package j.se.concurrency.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();
	final Object[] items = new Object[100];
	int putptr, takeptr, count;

	public void put(Object x) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length) {
				System.out.println(Thread.currentThread().getName() + " : " + "the array is full");
				notFull.await();//await主要做了两件事：释放锁，然后挂起当前线程
			}
			items[putptr] = x;
			if (++putptr == items.length) {
				putptr = 0;
			}
			++count;
			System.out.println(Thread.currentThread().getName() + " : " + "Add value :" + x + " ,Current putptr is : " + putptr + " ,the length is : " + count);
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0) {
				System.out.println("the array is empty");
				notEmpty.await();
			}
			Object x = items[takeptr];
			if (++takeptr == items.length) {
				takeptr = 0;
			}
			--count;
			System.out.println(Thread.currentThread().getName() + " : " + "Take value :" + x + " ,Current putptr is : " + putptr + " ,the length is : " + count);
			notFull.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}
}
