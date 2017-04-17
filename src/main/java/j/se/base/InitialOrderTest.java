package j.se.base;

import java.util.HashMap;

public class InitialOrderTest {
	// 静态变量
	public static String staticField = "静态变量";
	// 变量
	public String field = "变量";
	public int a = 9;
	// 静态初始化块
	static {
		System.out.println(staticField);
		System.out.println("静态初始化块");
	}

	// 初始化块
	{
		System.out.println(field);
		System.out.println("初始化块");
	}

	// 构造器
	public InitialOrderTest() {
		k();
		System.out.println("构造器  " + a);
	}

	void k() {
		System.out.println("k  " + a);
	}

	public static void main(String[] args) {
		// new InitialOrderTest();
		HashMap<String, String> h1 = new HashMap<String, String>(11);
		h1.put("apple", "red");
		h1.put("peach", "yellow");
		h1.put("mango", "orange");
		String everything = h1.toString();
		System.out.println(everything);
	}

}
