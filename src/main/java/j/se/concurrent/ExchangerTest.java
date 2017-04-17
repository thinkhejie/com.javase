package j.se.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java 并发 API 提供了一种允许2个并发任务间相互交换数据的同步应用。
 * 更具体的说，Exchanger 类允许在2个线程间定义同步点，当2个线程到达这个点，
 * 他们相互交换数据类型，使用第一个线程的数据类型变成第二个的，然后第二个线程的数据类型变成第一个的。
   这个类在遇到类似生产者和消费者问题时，是非常有用的。
   来一个非常经典的并发问题：你有相同的数据buffer，一个或多个数据生产者，和一个或多个数据消费者。
   只是Exchange类只能同步2个线程，所以你只能在你的生产者和消费者问题中只有一个生产者和一个消费者时使用这个类。
   在这个指南，你将学习如何使用 Exchanger 类来解决只有一个生产者和一个消费者的生产者和消费者问题。
 * @author lixing.hj
 *
 */
public class ExchangerTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Exchanger exchanger = new Exchanger();
		service.execute(new Runnable() {
			@Override
			public void run() {
				try {

					String data1 = "zxx";
					System.out.println("�߳�" + Thread.currentThread().getName() + "���ڰ����" + data1 + "����ȥ");
					Thread.sleep((long) (Math.random() * 10000));
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("�߳�" + Thread.currentThread().getName() + "���ص����Ϊ" + data2);
				} catch (Exception e) {

				}
			}
		});
		service.execute(new Runnable() {
			@Override
			public void run() {
				try {

					String data1 = "lhm";
					System.out.println("�߳�" + Thread.currentThread().getName() + "���ڰ����" + data1 + "����ȥ");
					Thread.sleep((long) (Math.random() * 10000));
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("�߳�" + Thread.currentThread().getName() + "���ص����Ϊ" + data2);
				} catch (Exception e) {

				}
			}
		});
	}
}
