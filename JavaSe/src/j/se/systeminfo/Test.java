package j.se.systeminfo;

public class Test {

	public static void main(String[] args) {
		int i = 2;
		for (f('A'); f('B') && i < 2; f('C')) {
			f('D');
		}
	}

	public static boolean f(char c) {
		System.out.println(c);
		return true;
	}
}
