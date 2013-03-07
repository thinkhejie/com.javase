package j.se.concurrency.executor;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExecutorServiceTest {

	public Object doWork() {
		//		try {
		//			Thread.sleep(3000);
		//		} catch (InterruptedException e) {
		//			e.printStackTrace();
		//		}
		for (int m = 0; m < 10000; m++) {
			System.out.println("" + m + ":" + new Date());
		}
		return "doWork";
	}

	public static void main(String[] agrs) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable<Object> task = new Callable<Object>() {
			public Object call() throws Exception {
				Object result = new ExecutorServiceTest().doWork();
				return result;
			}
		};
		Future future = executor.submit(task);
		try {
			future.get(1, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
			future.cancel(true);
			executor.shutdownNow();
		}
		//executor.shutdown();
	}
}
