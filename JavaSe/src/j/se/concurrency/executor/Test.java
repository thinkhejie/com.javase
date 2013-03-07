package j.se.concurrency.executor;

import j.se.concurrency.tool.DoBusi;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args) {
		Timer t = new Timer();
		ExecutorService service = Executors.newSingleThreadExecutor();
		final DoBusi business = new DoBusi();
		//		t.schedule(new TimerTask() {
		//			@Override
		//			public void run() {
		//				ThreadControl.stopThread(business);
		//			}
		//		}, 20 * 1000);
		//ThreadControl.startThread(business);
		//service.execute(business);
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				//ThreadControl.stopThread(business);
			}
		}, 30 * 1000);
	}

	@org.junit.Test
	public void kkkk() throws InterruptedException {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		service.awaitTermination(10, TimeUnit.SECONDS);
		//service.scheduleAtFixedRate(command, initialDelay, period, unit);
		service.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("DoBusi....");
			}
		});
	}
}
