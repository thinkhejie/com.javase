package j.se.concurrency.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		class MyCallable implements Callable<String> {
			@Override
			public String call() throws Exception {
				for (int i = 0; i < 10; i++) {
					Thread.sleep(1000);
					System.out.println("calling " + i);
				}
				return "hello";
			}

		}
		//Future<String> future = service.submit(new MyCallable());
		@SuppressWarnings("unused")
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

		Future<String> future = service.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				for (int i = 0; i < 10; i++) {
					Thread.sleep(1000);
					System.out.println("calling " + i);
				}
				return "hello";
			}

		});

		try {
			//���������������ͣʧ�ܣ����ܿ��������ӡ��calling��
			//Thread.sleep(2000);			
			//future.cancel(false);
			//System.out.println(future.isCancelled());
			if (true) {
				System.out.println(future.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end!");
		service.shutdown();
		/*
		 * 考虑以下场景：浏览网页时，浏览器用了5个线程下载网页中的图片文件，由于图片大小、网站访问速度等诸多因素的影响，完成图片下载的时间就会有很大的不同。先下载完成的图片就会被先显示到界面上，反之，后下载的图片就后显示。
		   Java的并发库的CompletionService可以满足这种场景要求。
		       该接口有两个重要方法：submit()和take()。
		   submit用于提交一个runnable或者callable，一般会提交给一个线程池处理；
		         而take就是取出已经执行完毕runnable或者callable实例的Future对象，如果没有满足要求的，就等待了。
		    CompletionService还有一个对应的方法poll，该方法与take类似，只是不会等待，如果没有满足要求，就返回null对象。
		 */
		CompletionService<String> ecs = new ExecutorCompletionService<String>(Executors.newSingleThreadExecutor());
		for (int i = 0; i < 10; i++) {
			ecs.submit(new MyCallable());
		}
		try {
			for (int i = 0; i < 10; i++) {
				Future<String> future2 = ecs.take();
				System.out.println(future2.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
