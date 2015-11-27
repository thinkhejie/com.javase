package j.se.thread;

public class ThreadGroupTest {
	public static void main(String[] args) {
		ThreadGroup tg = new ThreadGroup("group1");
		System.out.println(tg.getName());
	}
}
