package j.se.base;

public class LearnArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		new LearnArray().test();

	}

	public void test() {
		double[] dbArray = new double[10];
		for (int i = 0; i <= 9; i++) {
			dbArray[i] = Math.random();
			System.out.println(i);
			System.out.println(dbArray[i]);
			System.out.println("    ");
		}

	}

}
