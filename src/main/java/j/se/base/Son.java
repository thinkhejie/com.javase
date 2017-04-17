package j.se.base;

public class Son extends Dady {
	public int a = 10;

	public Son() {
		sprint();
	}

	@Override
	public String fun() {
		return null;
	}

	public void printA() {
		System.out.println(a);
	}

	public void printParentA() {
		System.out.println(super.a);
	}

	@Override
	public void sprint() {
		System.out.println("Son:  " + a);
	}

}
