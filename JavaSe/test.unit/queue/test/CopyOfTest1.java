package queue.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CopyOfTest1 {
	static LinkedBlockingQueue queue = new LinkedBlockingQueue();

	public static void add(String str) {
		try {
			queue.put(str);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//
	//1、送数据线程， 无界线程
	//2、调用外接口线程、就一个有界、如果队列中没有数据则进行等待，即队列进入阻塞状态。
	static ExecutorService exe = Executors.newFixedThreadPool(20);//1
	static ScheduledExecutorService exe1 = Executors.newSingleThreadScheduledExecutor();//2

	/**/
	public static void main(String[] args) {
		System.err.println("队列启动。。。");
		//		for (int i = 0; i < 2; i++) {
		//			exe.execute(new Runnable() {
		//				@Override
		//				public void run() {
		//					while (true) {
		//						try {
		//							Thread.sleep((long) (Math.random() * 1000));
		//							System.out.println("当前线程: " + Thread.currentThread().getName());
		//							queue.put(1);
		//							System.out.println("当前线程: " + Thread.currentThread().getName() + "对列大小: " + queue.size());
		//						} catch (InterruptedException e) {
		//							e.printStackTrace();
		//						}
		//					}
		//				}
		//			});
		//		}
		//2.0发送数据
		long initialDelay = 0;
		long period = 5000L;
		//long nano1 = System.currentTimeMillis();
		exe1.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					Object t = queue.take();
					Calendar rightnow = java.util.Calendar.getInstance();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					System.err.println("当前时间： " + format.format(rightnow.getTime()) + ",当前线程: " + Thread.currentThread().getName() + "取走：" + String.valueOf(t));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, initialDelay, period, TimeUnit.MILLISECONDS);
	}
}
