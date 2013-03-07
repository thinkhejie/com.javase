package arithmetic;

public class TestOne implements Runnable {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new TestOne());
		t.start();
		System.out.println("start");
		t.join();
		System.out.println("end");
	}

	/**
	 * @param args
	 */
	@Override
	public void run() {
		System.out.println("5555");
	}
}
