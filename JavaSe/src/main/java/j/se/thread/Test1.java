package j.se.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class Test1 {

	private final int POOL_SIZE = 4; //单个CPU的线程池大小

	@Test
	public void test() {
		int i = Runtime.getRuntime().availableProcessors();
		System.out.println("内核个数为:" + i);
		ExecutorService exe = Executors.newFixedThreadPool(i * POOL_SIZE);
		exe.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("马总碉堡了");

			}
		});
	}
}
