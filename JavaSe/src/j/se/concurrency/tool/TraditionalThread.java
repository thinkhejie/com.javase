package j.se.concurrency.tool;

import java.util.Timer;
import java.util.TimerTask;

public class TraditionalThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			}
		}.start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			}
		}).start();

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		}, 10000, 1000);
	}

}
