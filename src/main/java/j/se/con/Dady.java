package j.se.con;

/**
 * 
 * @author hejie
 *
 */
public class Dady {
	static int b;
	public int a = 1;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public Dady() {
		// staticprint();
		sprint();
	}

	public Object fun() {
		return null;
	}

	public void sprint() {
		System.out.println("Dady��sprint:  " + a);
	}

	public void ssprint() {
		System.out.println("ssprint:  " + a);
	}

	public void staticprint() {
		System.out.println("Dady:  " + b);
	}
}
