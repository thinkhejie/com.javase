package j.se.concurrency.executor;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		Runnable runnable = new Runnable() {
			A a = new A();

			@Override
			public void run() {
				//MyData.x.set(new Random().nextInt(10000));
				MyData.getMyDate().setY(new Random().nextInt(10000));
				a.say();
			}
		};
		service.execute(runnable);
		service.execute(runnable);
		service.shutdown();
	}

}

class MyData {
	public static ThreadLocal x = new ThreadLocal();

	public static void set(Object value) {
		x.set(value);
	}

	public static Object get() {
		return x.get();
	}

	private static ThreadLocal data = new ThreadLocal();

	/*锟斤拷锟节诧拷同锟斤拷锟竭筹拷锟斤拷说锟斤拷getMyData锟矫碉拷锟侥讹拷锟襟都诧拷锟斤拷同锟斤拷
	 * 锟斤拷同一锟斤拷锟竭筹拷锟斤拷说锟斤拷锟斤拷锟斤拷getMyData锟斤拷锟劫次猴拷锟斤拷锟斤拷锟斤拷getMyData锟斤拷锟矫碉拷锟侥讹拷锟斤拷同一锟斤拷*/
	public static MyData getMyDate() {
		MyData myData = (MyData) data.get();
		if (myData == null) {
			myData = new MyData();
			data.set(myData);
		}
		return myData;
	}

	private MyData() {
	}

	private Integer y;

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getY() {
		return y;
	}
}

class A {
	public void say() {
		//System.out.println("say " + Thread.currentThread().getName() + " geted " + MyData.x.get());
		System.out.println("say " + Thread.currentThread().getName() + " geted " + MyData.getMyDate().getY());

		new B().sayHello();
	}
}

class B {

	public void sayHello() {
		//System.out.println("sayHello " +Thread.currentThread().getName() + " geted " + MyData.x.get());
		System.out.println("sayHello " + Thread.currentThread().getName() + " geted " + MyData.getMyDate().getY());

	}

}
