package j.se.concurrency.executor;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo2 {

	public static void main(String[] args) {
		//	ExecutorService service = Executors.newSingleThreadExecutor();
		//		class MyCallable implements Callable<String> {
		//			@Override
		//			public String call() throws Exception {
		//				for (int i = 0; i < 10; i++) {
		//					Thread.sleep(1000);
		//					System.out.println("calling " + i);
		//				}
		//				return "hello";
		//			}
		//
		//		}
		//Future<String> future = service.submit(new MyCallable());
		//		@SuppressWarnings("unused")
		//		class MyCallable2<T> implements Callable<T> {
		//
		//			@Override
		//			public T call() throws Exception {
		//				for (int i = 0; i < 10; i++) {
		//					Thread.sleep(1000);
		//					System.out.println("calling " + i);
		//				}
		//				return null;
		//			}
		//		}

		//		Future<String> future = service.submit(new Callable<String>() {
		//			@Override
		//			public String call() throws Exception {
		//				for (int i = 0; i < 10; i++) {
		//					Thread.sleep(1000);
		//					System.out.println("calling " + i);
		//				}
		//				return "hello";
		//			}
		//
		//		});

		//try {
		//			//���������������ͣʧ�ܣ����ܿ��������ӡ��calling��
		//			//Thread.sleep(2000);			
		//			//future.cancel(false);
		//			//System.out.println(future.isCancelled());
		//			if (true) {
		//				System.out.println(future.get());
		//			}
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
		//		System.out.println("end!");
		//		service.shutdown();
		try {
			CompletionService<String> ecs = new ExecutorCompletionService<String>(Executors.newFixedThreadPool(100));
			for (int i = 0; i < 10; i++) {
				ecs.submit(new MyCallable());
			}
			for (int i = 0; i < 10; i++) {
				Future<String> future2 = ecs.take();
				System.out.println(future2.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
