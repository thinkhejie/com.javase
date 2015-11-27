package j.se.base;

public class InitialOrderTest {

	/**
	 * @param args
	 * 我们大家都知道，对于静态变量、静态初始化块、变量、初始化块、构造器，它们的初始化顺序以此是（静态变量、静态初始化块）>（变量、初始化块）>构造器
	 */
	// 构造器
	public InitialOrderTest() {
		System.out.println("构造器");
	}

	// 变量
	public String field = "变量";
	//初始化块
	{
		System.out.println(field);
		System.out.println("初始化块");
	}
	// 静态变量
	public static String staticField = "静态变量";
	// 静态初始化块
	static {
		String field1 = "静态初始化块里的变量";
		System.out.println(staticField);
		System.out.println(field1);
		System.out.println("静态初始化块");
	}

	public static void main(String[] args) {
		//TODO Auto-generated method stub
		new InitialOrderTest();
	}
}
