package j.se.concurrency.collections;

import java.util.Random;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * Java 7中引入了TransferQueue，它比BlockingQueue多了一个叫transfer的方法
 * 如果接收线程处于等待状态，该操作可以马上将任务交给它，否则就会阻塞直至取走该任务的线程出现。
 * 可以用TransferQueue代替BlockingQueue，因为它可以获得更好的性能。
 * [无界的队列，具有先进先出(FIFO)的特性]
 * @author hejie
 1. transfer(E e)：若当前存在一个正在等待获取的消费者线程，即立刻移交之；否则，会插入当前元素e到队列尾部，并且等待进入阻塞状态，到有消费者线程取走该元素。
 2. tryTransfer(E e)：若当前存在一个正在等待获取的消费者线程（使用take()或者poll()函数），使用该方法会即刻转移/传输对象元素e；若不存在，则返回false，并且不进入队列。这是一个不阻塞的操作。
 3. tryTransfer(E e, long timeout, TimeUnit unit)：若当前存在一个正在等待获取的消费者线程，会立即传输给它;否则将插入元素e到队列尾部，并且等待被消费者线程获取消费掉；若在指定的时间内元素e无法被消费者线程获取，则返回false，同时该元素被移除。
 4. hasWaitingConsumer()：判断是否存在消费者线程。
 5. getWaitingConsumerCount()：获取所有等待获取元素的消费线程数量。
 6.size()：因为队列的异步特性，检测当前队列的元素个数需要逐一迭代，可能会得到一个不太准确的结果，尤其是在遍历时有可能队列发生更改。
 7.批量操作：类似于addAll，removeAll, retainAll, containsAll, equals, toArray等方法，API不能保证一定会立刻执行。因此，我们在使用过程中，不能有所期待，这是一个具有异步特性的队列。
注意事项：
A、无论是transfer还是tryTransfer方法，在>=1个消费者线程等待获取元素时（此时队列为空），都会立刻转交，这属于线程之间的元素交换。注意，这时，元素并没有进入队列。
B、在队列中已有数据情况下，transfer将需要等待前面数据被消费掉，直到传递的元素e被消费线程取走为止。
C、使用transfer方法，工作者线程可能会被阻塞到生产的元素被消费掉为止。
D、消费者线程等待为零的情况下，各自的处理元素入队与否情况有所不同。
E、size()方法，需要迭代，可能不太准确，尽量不要调用。
 */
public class TransferQueueTest {
	
	public static void main(String[] args) {
		TransferQueue<String> queue = new LinkedTransferQueue<String>();
		Thread producer = new Thread(new Producer(queue));
		producer.setDaemon(false); // 设置为守护进程使得线程执行结束后程序自动结束运行
		producer.start();
		for (int i = 0; i < 10; i++) {
			//Thread consumer = new Thread(new Consumer(queue));
			//consumer.setDaemon(true);
			//consumer.start();
			try {
				// 消费者进程休眠一秒钟，以便以便生产者获得CPU，从而生产产品
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static class Producer implements Runnable {  
	    private final TransferQueue<String> queue;  
	  
	    public Producer(TransferQueue<String> queue) {  
	        this.queue = queue;  
	    }  
	  
	    private String produce() {  
	        return " your lucky number " + (new Random().nextInt(100));  
	    }  
	  
	    @Override  
	    public void run() {  
	        try {
	            while (true) {
	                //if (queue.hasWaitingConsumer()) {  
	                    queue.transfer(produce());//阻塞方法
	                    //System.out.println(isTrans);
	               // }
	                TimeUnit.SECONDS.sleep(1);//生产者睡眠一秒钟,这样可以看出程序的执行过程 
	                System.out.println(queue.size());
	            }  
	        } catch (InterruptedException e) {  
	        }  
	    }  
	} 
	
	public static class Consumer implements Runnable {  
	    private final TransferQueue<String> queue;  
	  
	    public Consumer(TransferQueue<String> queue) {  
	        this.queue = queue;  
	    }  
	  
	    @Override  
		public void run() {
			while (true) {
				try {
					System.out.println(" Consumer " + Thread.currentThread().getName() + queue.take());
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		}
	}  
}
