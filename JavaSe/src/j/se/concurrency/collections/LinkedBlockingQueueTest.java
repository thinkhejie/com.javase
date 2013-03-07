package j.se.concurrency.collections;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {

	public void test() {
		LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<String>();
		lbq.add("A");
		lbq.add("A");
	}
}
