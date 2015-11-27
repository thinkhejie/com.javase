package j.se.base;

public class TestOrder {

	/**
	 * @param args
	 * 运行上面的代码，会得到如下的结果：
	 *
	 *   Test--A 
	 *   静态初始化块 
	 *   Test--B 
	 *   
	 *  大家可以随意改变变量a、变量b以及静态初始化块的前后位置，
	 *	就会发现输出结果随着它们在类中出现的前后顺序而改变，
	 *	这就说明静态变量和静态初始化块是依照他们在类中的定义顺序进行初始化的。
	 *  同样，变量和初始化块也遵循这个规律。
	 */

	// 静态变量    
	public static TestA a = new TestA();
	// 静态初始化块    
	static {
		System.out.println("静态初始化块");
	}
	// 静态变量    
	public static TestB b = new TestB();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestOrder();
	}
}
