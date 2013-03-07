package j.se.base;

public class TestAssert {

	/**
	 * @param args
	 */
	void kk() {
		int xxx;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int s;
		for (int i = 0; i < 10; i++) {
			try {
				assert i % 2 == 0;
				i--;
				System.out.println("Envn Number:" + i);
			} catch (AssertionError ae) {
				// TODO Auto-generated catch block
				System.out.println("Olld Number :" + ++i);
			}
			int k = 5;

		}

	}

}
