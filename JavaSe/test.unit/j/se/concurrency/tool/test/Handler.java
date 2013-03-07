package j.se.concurrency.tool.test;

public class Handler {

	public void invoke() {
		System.err.println(Thread.currentThread().getName() + " invoke..." + "执行时间： " + System.currentTimeMillis());
	}
}
