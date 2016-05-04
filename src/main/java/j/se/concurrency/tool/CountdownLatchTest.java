package j.se.concurrency.tool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 计数器
 * CountDownLatch是一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
    // 使当前线程在锁存器倒计数至零之前一直等待，除非线程被中断。
	void await()
	// 使当前线程在锁存器倒计数至零之前一直等待，除非线程被中断或超出了指定的等待时间。
	boolean await(long timeout, TimeUnit unit)
	// 递减锁存器的计数，如果计数到达零，则释放所有等待的线程。
	void countDown()
	// 返回当前计数。
	long getCount()
	// 返回标识此锁存器及其状态的字符串。
	String toString()
 */
public class CountdownLatchTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);//发布命令
		final CountDownLatch cdAnswer = new CountDownLatch(3);
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("线程" + Thread.currentThread().getName() + "正准备接受命令");
						cdOrder.await();//等待点
						System.out.println("线程" + Thread.currentThread().getName() + "已接受命令");
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "回应命令处理结果");
						cdAnswer.countDown();//减一
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		//
		try {
			//Main线程运行
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println("线程:" + Thread.currentThread().getName() + "即将发布命令");
			cdOrder.countDown();//减一
			System.out.println("线程:" + Thread.currentThread().getName() + "已发送命令，正在等待结果");
			cdAnswer.await();//等待点
			System.out.println("线程:" + Thread.currentThread().getName() + "已收到所有响应结果");
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.shutdown();
	}
}
