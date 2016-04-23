package j.se.concurrency.collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class DequeTest {
	public static void main(String[] args) throws ClassNotFoundException {
		//名称 deque 是“double ended queue（双端队列）”的缩写，通常读为“deck”。
		//特别说明的是ArrayDeque并不是一个固定大小的队列，每次队列满了以后就将队列容量扩大一倍(doubleCapacity())，因此加入一个元素总是能成功，
		//而且也不会抛出一个异常。也就是说ArrayDeque是一个没有容量限制的队列。
		//
		Deque d1 = new ArrayDeque();//不是线程安全的
		//它是一个基于链接节点的无界线程安全队列。此队列按照 FIFO（先进先出）原则对元素进行排序。队列的头部 是队列中时间最长的元素。
		//队列的尾部 是队列中时间最短的元素。新的元素插入到队列的尾部，队列获取操作从队列头部获得元素。
		//Deque d2 = new ConcurrentLinkedDeque();//无界的，非阻塞，线程安全的并发队列(FIFO LIFO)
		Queue q1 = new ConcurrentLinkedQueue();
		Deque d3 = new LinkedBlockingDeque();
	}
}
