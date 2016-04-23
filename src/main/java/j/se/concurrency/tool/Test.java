package j.se.concurrency.tool;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class Test {

	public static void main(String[] args) {
		/*
		  Semaphore 信号量，就是一个允许实现设置好的令牌。也许有1个，也许有10个或更多。 
		    谁拿到令牌(acquire)就可以去执行了，如果没有令牌则需要等待。 
		    执行完毕，一定要归还(release)令牌，否则令牌会被很快用光，别的线程就无法获得令牌而执行下去了。 
		 */
		final Semaphore semaphore = new Semaphore(1);
		final SynchronousQueue<String> queue = new SynchronousQueue<String>();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						semaphore.acquire();
						String input = queue.take();
						String output = TestDo.doSome(input);
						System.out.println(Thread.currentThread().getName() + ":" + output);
						semaphore.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		for (int i = 0; i < 10; i++) { //���в��ܸĶ�
			String input = i + ""; //���в��ܸĶ�
			try {
				queue.put(input);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

//���ܸĶ���TestDo��
class TestDo {
	public static String doSome(String input) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String output = input + ":" + (System.currentTimeMillis() / 1000);
		return output;
	}
}
