package arithmetic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {

	public static final int Easy = 3;

	/**
	 * 
	 * 
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		List<Integer> lis = new LinkedList<Integer>();
		lis.add(5);
		lis.add(-2);
		lis.add(7);
		System.out.println(lis);
		ArrayList<String> lia = new ArrayList<String>();
		lia.add("d");
		lia.add("c");
		lia.add("b");
		System.out.println(lia);
		// Vector vc = new Vector<Test>();
		int ii = Integer.parseInt("two");
		// parseInt("5");
		Integer.valueOf(6);
		Father f = new Son();
		getcheck(f);
		int i = 5;
		// String s = null;
		// testString(s);
		kk(++i);
		System.out.println(i);
		Object o = new String("abcd");
		String s = (String) o;
		System.out.println(s);
		System.out.println(o);
	}

	/**
	 * @param args
	 */
	public static void getcheck(Father c) {
		if (c instanceof Father) {
			System.out.println("Father");

		} else if (c instanceof Son) {
			System.out.println("Son");

		} else {
			System.out.println("none");
		}
	}

	/**
	 * @param args
	 * 
	 * 
	 * 
	 */
	public static void testString(String str) {
		if (str == null | str.length() == 0) {
			System.out.println("String is empty");
		} else {
			System.out.println("String is not empty");
		}
	}

	/**
	 * @param args
	 */
	public static void kk(int str) {

	}
}
