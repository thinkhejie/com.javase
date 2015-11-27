package j.se.concurrency.locks;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
	public static void main(String[] args) {
		final Queue3 q3 = new Queue3();
		for (int i = 0; i < 3; i++) {
			new Thread() {
				@Override
				public void run() {
					while (true) {
						q3.get();
					}
				}

			}.start();

			new Thread() {
				@Override
				public void run() {
					while (true) {
						q3.put(new Random().nextInt(10000));
					}
				}

			}.start();
		}

	}
}

/*
 * 
3、ReentrantReadWriteLock为2个锁提供了可重进入的加锁语义。

与ReentrantLock相同，ReentrantReadWriteLock能被构造成非公平或公平的。
 
在公平锁中，选择权交给等待时间最长的线程；如果锁由读者获得而一个线程请求写入锁，那不再允许读者获得读取锁，直到写者被受理，并且已经释放了写入锁。
 
在非公平锁中，线程允许访问的顺序是不定的。由写者降级为读者是允许的；从读者升级为写得是不允许的。
 
ReentrantReadWriteLock的写入锁有一个唯一的所有者。

 */
class Queue3 {
	private Object data = null;
	ReadWriteLock rwl = new ReentrantReadWriteLock();

	public void get() {
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to read data!");
			Thread.sleep((long) (Math.random() * 1000));
			System.out.println(Thread.currentThread().getName() + "have read data :" + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwl.readLock().unlock();
		}
	}

	public void put(Object data) {
		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to write data!");
			Thread.sleep((long) (Math.random() * 1000));
			this.data = data;
			System.out.println(Thread.currentThread().getName() + " have write data: " + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwl.writeLock().unlock();
		}
	}
}
