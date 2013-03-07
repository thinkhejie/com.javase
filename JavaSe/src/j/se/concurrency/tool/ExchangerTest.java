package j.se.concurrency.tool;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 交换数据,当两个线程同时到达一个时间点时进行交换
 */
public class ExchangerTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Exchanger exchanger = new Exchanger();
		service.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String data1 = "a";
					System.out.println("线程" + Thread.currentThread().getName() + "正在把数据" + data1 + "换出去");
					//Thread.sleep((long) (Math.random() * 10000));
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为" + data2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//
		service.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String data1 = "b";
					System.out.println("线程" + Thread.currentThread().getName() + "正在把数据" + data1 + "换出去");
					//	Thread.sleep((long) (Math.random() * 2000));
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为" + data2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
