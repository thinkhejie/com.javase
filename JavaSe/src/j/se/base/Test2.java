package j.se.base;

public class Test2 {
	public static void main(String[] args) {
		Accout a = new Accout("你", 3000);
		a.setOp(Accout.sub);
		AccoutUser au1 = new AccoutUser(a, 2000);
		Thread t1 = new Thread(au1, "你");
		Thread t2 = new Thread(au1, "你老婆");
		t1.start();
		t2.start();
	}
}