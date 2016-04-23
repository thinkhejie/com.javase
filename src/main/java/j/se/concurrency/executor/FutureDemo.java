package j.se.concurrency.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class MyCallable implements Callable<String> {
	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName());
		for (int i = 0; i < 10000; i++) {
			//Thread.sleep(1000);
			System.out.println("calling " + i);
		}
		return "hello";
	}
}

class MyCallable2<T> implements Callable<T> {
	@Override
	public T call() throws Exception {
		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			System.out.println("calling " + i);
		}
		return null;
	}
}

class MyCallable3 implements Callable<String> {
	@Override
	public String call() throws Exception {
		while (true) {
			System.out.println("call");
		}
	}
}

public class FutureDemo {
	static ExecutorService service = Executors.newSingleThreadExecutor();
	
	public static void main(String[] args) {
		try {
			Future<String> future = service.submit(new MyCallable());
			//Thread.sleep(2000);			
			//future.cancel(false);
			//System.out.println(future.isCancelled());
			//if (true) {
			System.out.println(future.get(1, TimeUnit.SECONDS));
			//System.out.println(future.get());
			//	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("end!");
		//service.shutdown();
		//		CompletionService<String> ecs = new ExecutorCompletionService<String>(Executors.newSingleThreadExecutor());
		//		for (int i = 0; i < 10; i++) {
		//			ecs.submit(new MyCallable());
		//		}
		//		try {
		//			for (int i = 0; i < 10; i++) {
		//				Future<String> future2 = ecs.take();
		//				System.out.println(future2.get());
		//			}
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
	}
}
