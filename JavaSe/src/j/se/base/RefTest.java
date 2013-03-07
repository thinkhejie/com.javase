package j.se.base;

import java.lang.reflect.Constructor;

public class RefTest {

	public static void main(String[] args) {
		Class cls;
		try {
			//产生一个叫做constructor2名字的Class的对象
			cls = Class.forName("Ref");

			//生成一个Class类型的数组，大小为2
			Class partypes[] = new Class[2];

			//数组中的Class类型都是整形
			partypes[0] = Integer.TYPE;
			partypes[1] = Integer.TYPE;

			//得到public constructor2(int a, int b)这个构造函数
			Constructor ct = cls.getConstructor(partypes);

			//生成一个对象数组，大小为2，分别是两个整形值
			Object arglist[] = new Object[2];
			arglist[0] = new Integer(37);
			arglist[1] = new Integer(47);

			//调用刚才的那个构造函数生成一个constructor2的实例，
			//其中构造函数中的参数就是刚才你的那个对象数组
			Object retobj = ct.newInstance(arglist);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
