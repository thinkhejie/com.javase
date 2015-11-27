package j.se.concurrency.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CopyOfFutureTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		ExecutorService service1 = Executors.newSingleThreadExecutor();
		Future<String> future = service.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				for (int i = 0; i < 10; i++) {
					//Thread.sleep(1000);
					System.out.println("calling " + i);
				}
				return "hello";
			}
		});

		try {
			if (true) {
				System.err.println("before ");
				System.out.println(future.get(1,TimeUnit.SECONDS));
				System.err.println("after ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end!");
		service.shutdown();
	}
}
