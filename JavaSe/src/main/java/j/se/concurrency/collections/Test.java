package j.se.concurrency.collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 
1、ArrayBlockingQueue在构造时需要指定容量，并可以选择是否需要公平性，如果公平参数被设置true，等待时间最长的线程会优先得到处理（其实就是通过将ReentrantLock设置为true来达到这种公平性的：即等待时间最长的线程会先操作）。通常，公平性会使你在性能上付出代价，只有在的确非常需要的时候再使用它。它是基于数组的阻塞循环队列，此队列按 FIFO（先进先出）原则对元素进行排序。

2、PriorityBlockingQueue是一个带优先级的队列，而不是先进先出队列。元素按优先级顺序被移除，该队列也没有上限（看了一下源码，PriorityBlockingQueue是对PriorityQueue的再次包装，是基于堆数据结构的，而PriorityQueue是没有容量限制的，与ArrayList一样，所以在优先阻塞队列上put时是不会受阻的。虽然此队列逻辑上是无界的，但是由于资源被耗尽，所以试图执行添加操作可能会导致 OutOfMemoryError），但是如果队列为空，那么取元素的操作take就会阻塞，所以它的检索操作take是受阻的。另外，往入该队列中的元素要具有比较能力。
 
3、DelayQueue（基于PriorityQueue来实现的）是一个存放Delayed 元素的无界阻塞队列，只有在延迟期满时才能从中提取元素。该队列的头部是延迟期满后保存时间最长的 Delayed 元素。如果延迟都还没有期满，则队列没有头部，并且poll将返回null。当一个元素的 getDelay(TimeUnit.NANOSECONDS) 方法返回一个小于或等于零的值时，则出现期满，poll就以移除这个元素了。此队列不允许使用 null 元素。 下面是延迟接口：

4、LinkedBlockingQueue FIFO 无界队列
 */
public class Test {

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(4);
		final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
		for (int i = 0; i < 4; i++) {
			es.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							String log = queue.take();
							parseLog(log);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
		long begin = System.currentTimeMillis();
		System.out.println("begin:" + begin);
		/*
		 模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。
		修改程序代码，开四个线程让这16个对象在4秒钟打完。
		*/
		for (int i = 0; i < 16; i++) { //这行代码不能改动
			final String log = "" + (i + 1);//这行代码不能改动
			{
				try {
					queue.put(log);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Test.parseLog(log);
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("end:" + end);
		System.out.println("耗费时间:" + (end - begin) / 1000 + "秒");
		es.shutdown();
	}

	//parseLog方法内部的代码不能改动
	public static void parseLog(String log) {
		System.out.println(Thread.currentThread().getName() + " " + log + ":" + (System.currentTimeMillis()));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}