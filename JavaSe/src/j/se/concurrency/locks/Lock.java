package j.se.concurrency.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lock {

	public void t() {
		ReadWriteLock rwl = new ReentrantReadWriteLock();
		rwl.readLock();
		rwl.writeLock();
	}

	public void t1() {
		java.util.concurrent.locks.Lock rwl = new ReentrantLock();//重入锁（ReentrantLock）是一种递归无阻塞的同步机制。
		rwl.tryLock();
		/*
		   •获取锁的时候将当前线程放入同步队列，并且将前一个节点的状态置为signal状态，然后阻塞
		   •当这个节点的前一个节点成功获取到锁，前一个节点就成了整个同步队列的head。
		   •当前一个节点释放锁的时候，它就唤醒当前线程的这个节点，然后当前线程的节点就可以成功获取到锁了
		   •这个时候它就到整个队列的头部了，然后release操作的时候又可以唤醒下一个。
		   AbstractQueuedSynchronizer通过构造一个基于阻塞的CLH队列容纳所有的阻塞线程，而对该队列的操作均通过Lock-Free（CAS）操作，但对已经获得锁的线程而言，ReentrantLock实现了偏向锁的功能。
		       公平锁保证一个阻塞的线程最终能够获得锁，因为是有序的，所以总是可以按照请求的顺序获得锁。
		       不公平锁意味着后请求锁的线程可能在其前面排列的休眠线程恢复前拿到锁，这样就有可能提高并发的性能。
		       这是因为通常情况下挂起的线程重新开始与它真正开始运行，二者之间会产生严重的延时。
		       因此非公平锁就可以利用这段时间完成操作。
		       这是非公平锁在某些时候比公平锁性能要好的原因之一。
		 */
	}

	public void t2() {
		java.util.concurrent.locks.Lock lock = new ReentrantLock();
		Condition notFull = lock.newCondition();
		Condition notEmpty = lock.newCondition();
		//Condition condition = new ConditionObjcet();
	}
}
