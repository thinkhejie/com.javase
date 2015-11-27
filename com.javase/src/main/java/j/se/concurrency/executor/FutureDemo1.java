package j.se.concurrency.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureDemo1 {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<String> future = service.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000L);
				return "Hello";
			}
		});
		//抛出超时异常
		try {
			String str = future.get(1L, TimeUnit.SECONDS);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("cancel");
			future.cancel(true);
		}
		System.out.println("end!");
		service.shutdown();
	}
}
