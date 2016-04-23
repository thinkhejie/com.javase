package j.se.concurrency.executor.test;

import j.se.concurrency.tool.test.HandlerClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
		//		Future future = pool.submit(new Callable() {
		//			@Override
		//			public Object call() throws Exception {
		//				for (;;) {
		//					long nano1 = System.currentTimeMillis();
		//					if (nano1 == 8325918815995L) {
		//						break;
		//					}
		//					System.out.println("hello: " + nano1);
		//				}
		//				return "ABC";
		//			}
		//		});
		//		Object obj = future.get();
		//		System.out.println("future: " + obj);
		//------------------
		//pool.execute(new HandlerClient());
		//------------------
		/* 创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；也就是将在 initialDelay 后开始执行，
		 * 然后在 initialDelay+period 后执行，接着在 initialDelay + 2 * period 后执行，依此类推。
		 */
		//千分之一秒  TimeUnit.MILLISECONDS
		//2002L
		//3002
		//5000
		//也就是说scheduleWithFixedDelay的执行开始时间为(delay+cost)，而对于scheduleAtFixedRate来说执行开始时间为max(period,cost)。
		long initialDelay = 10L;
		long period = 2000L;
		long nano1 = System.currentTimeMillis();
		System.out.println(nano1);
		//pool.execute(new HandlerClient());
		//第一次执行在initialDelay后，以后每period执行一次
		//ScheduledFuture future1 = pool.scheduleAtFixedRate(new HandlerClient(), initialDelay, period, TimeUnit.MILLISECONDS);
		ScheduledFuture future = pool.scheduleWithFixedDelay(new HandlerClient(), initialDelay, 1000L * 20, TimeUnit.MILLISECONDS);
		//System.out.println("future: " + future);
		//ScheduledFuture future = pool.scheduleWithFixedDelay(new HandlerClient(), initialDelay, 1, TimeUnit.MILLISECONDS);
		//System.out.println(System.nanoTime() - nano1);
		//pool.shutdown();
		/*创建并执行一个在给定初始延迟后首次启用的定期操作，随后，在每一次执行终止和下一次执行开始之间都存在给定的延迟。*/
		//	pool.scheduleWithFixedDelay(new HandlerClient(), initialDelay, delay, unit);

	}
}
