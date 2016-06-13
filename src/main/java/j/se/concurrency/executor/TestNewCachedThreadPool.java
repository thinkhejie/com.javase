package j.se.concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestNewCachedThreadPool {

	public static void main(String[] args) {
		System.out.println("最大内存量 is " + Runtime.getRuntime().maxMemory() / 1024 / 1024);// 对应-Xmx
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 3000; i++)
			service.submit(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(10 * 1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
	}
}
