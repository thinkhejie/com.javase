package j.se.base;

//2.写了一个模拟运行测试的类.代码如下: 
public class AccoutUser implements Runnable {

	Accout a;

	AccoutUser(Accout a, double x) {
		this.a = a;
		a.setNumber(x);
	}

	@Override
	public void run() {
		a.op();
	}
}
