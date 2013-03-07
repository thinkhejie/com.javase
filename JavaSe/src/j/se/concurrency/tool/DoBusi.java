package j.se.concurrency.tool;

public class DoBusi implements IRun {

	private boolean isRun = false;

	@Override
	public void run() {
		while (isRun) {
			System.out.println("DoBusi....");
		}
	}

	@Override
	public synchronized void start() {
		System.out.println("准备启动......");
		isRun = true;
		System.out.println("已经启动......");
	}

	@Override
	public synchronized void stop() {
		System.out.println("准备停止......");
		isRun = false;
		System.out.println("已停止。。。。");
	}
}
