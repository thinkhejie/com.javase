package j.se.concurrent;

import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueCommunication2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BlockingQueue<String> queue1 = new ArrayBlockingQueue<String>(2);
		queue1.offer("a");
		queue1.offer("b");
		System.out.println(queue1.poll());
		queue1.offer("c");
		
		//---------
		//    b      1-------
	    //                   |
		//---------           |
		//    a      0       
		//---------  
	}

	static class Business {
		BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
		BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);
		{
			Collections.synchronizedMap(null);
			System.out.println("xxxxxdfsdsafdsa");
			queue2.offer(1);
		}
		public void sub(int i) {
			try {
				queue1.put(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int j = 1; j <= 10; j++) {
				System.out.println("sub thread sequece of " + j + ",loop of " + i);
			}
			try {
				queue2.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void main(int i) {
			try {
				queue2.put(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			for (int j = 1; j <= 100; j++) {
				System.out.println("main thread sequece of " + j + ",loop of " + i);
			}
			try {
				queue1.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
