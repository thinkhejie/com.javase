package j.se.concurrency.tool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
	static class Worker extends Thread {
		CountDownLatch latch;
		String workerName;
		int workTime;

		public Worker(String workerName, int workTime, CountDownLatch latch) {
			this.workerName = workerName;
			this.workTime = workTime;
			this.latch = latch;
		}

		private void doWork() {
			try {
				Thread.sleep(workTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			System.out.println("Worker " + workerName + " do work begin at " + sdf.format(new Date()));
			doWork();//工作了                                                                         
			System.out.println("Worker " + workerName + " do work complete at " + sdf.format(new Date()));
			latch.countDown();//工人完成工作，计数器减一                                              
		}
	}

	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws Exception {
		CountDownLatch latch = new CountDownLatch(2);//两个工人的协作                                    
		Worker worker1 = new Worker("张三", 5000, latch);
		Worker worker2 = new Worker("李四", 8000, latch);
		worker1.start();//
		worker2.start();//
		latch.await(8, TimeUnit.SECONDS);//等待所有工人完成工作,才能往下执行
		System.out.println("all work done at " + sdf.format(new Date()));
	}
}
