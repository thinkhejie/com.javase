package j.se.concurrency.tool.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Test {

	public static void main(String[] args) {
		Semaphore sp = new Semaphore(1);
		Map map = new ConcurrentHashMap();
		ExecutorService exe = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 10; i++) {
			exe.execute(new HandlerClient("1", sp, map));
			exe.execute(new HandlerClient("1", sp, map));
			exe.execute(new HandlerClient("1", sp, map));
		}
		//		exe.execute(new Runnable() {
		//			@SuppressWarnings("static-access")
		//			@Override
		//			public void run() {
		//				while (true) {
		//					try {
		//						new Handler().invoke();
		//						Thread.currentThread().sleep(1000);
		//					} catch (InterruptedException e) {
		//						e.printStackTrace();
		//					}
		//				}
		//			}
		//		});
		//		//
		//		exe.execute(new Runnable() {
		//			@SuppressWarnings("static-access")
		//			@Override
		//			public void run() {
		//				while (true) {
		//					try {
		//						new Handler().invoke();
		//						Thread.currentThread().sleep(1000);
		//					} catch (InterruptedException e) {
		//						e.printStackTrace();
		//					}
		//				}
		//			}
		//		});
	}
}
