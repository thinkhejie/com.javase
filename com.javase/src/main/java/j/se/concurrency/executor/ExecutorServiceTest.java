package j.se.concurrency.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExecutorServiceTest {

	public Object doWork() {
		for (int m = 0; m < 10000000; m++) {
//							try {
//								Thread.sleep(1000);
//							} catch (InterruptedException e) {
//								e.printStackTrace();
//							}
			//System.out.println("" + m + ":" + new Date());
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
		/*
		List<Callable<Object>> list  = new ArrayList<Callable<Object>>();
		list.add(task);
		List<Future<Object>> future = executor.invokeAll(list,10, TimeUnit.MILLISECONDS);
		*/
		Future<Object> future = executor.submit(task);
		try {
			future.get(1, TimeUnit.SECONDS);
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
