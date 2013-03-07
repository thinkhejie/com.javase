package threaddemo.test;

public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//		TreadLocalDemo td = new TreadLocalDemo();
		//		Thread t1 = new Thread(td, "a");
		//		Thread t2 = new Thread(td, "b");
		//		t1.start();
		//		t2.start();
		int oldCapacity = 1;
		oldCapacity = oldCapacity >> 1;
		System.out.println(oldCapacity);//0
	}

	@org.junit.Test
	public void test() {
		String str = "abcdefg";
		String dest = "";
		int i = str.length();
		for (int m = 1; m <= i; m++) {
			char c = str.charAt(i - m);
			dest = dest.concat(String.valueOf(c));
		}
		System.out.println(str);
		System.out.println(dest);
	}

	@org.junit.Test
	public void test1() {
		String str = "abcdef";
		char[] cs = str.toCharArray();
		int num = cs.length;
		for (int k = 0; k < num / 2; k++) {
			char c1 = cs[k];
			char c2 = cs[num - 1 - k];
			cs[k] = c2;
			cs[num - 1 - k] = c1;
		}
		System.out.println(String.valueOf(cs));
	}
}
