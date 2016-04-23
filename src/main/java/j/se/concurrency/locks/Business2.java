package j.se.concurrency.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Business2 {
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	boolean bShouldSub = true;

	public void sub() {
		lock.lock();
		if (!bShouldSub) {
			try {
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + " : " + i + " ,bShouldSub: " + bShouldSub);
			}
			bShouldSub = false;
			condition.signal();
		} finally {
			lock.unlock();
		}
	}

	public void main() {
		lock.lock();
		if (bShouldSub) {
			try {
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + " : " + i + " ,bShouldSub: " + bShouldSub);
			}
			bShouldSub = true;
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
}
