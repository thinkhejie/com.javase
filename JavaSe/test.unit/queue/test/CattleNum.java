package queue.test;

public class CattleNum {
	static int a = 2;
	static int b = 3;
	static int c = 4;
	static int result;

	public static void main(String[] args) {
		int n = 20;
		System.out.println("result " + cattle1(n));
	}

	public static int cattle1(int n) {
		if (n <= 3) {
			return n + 1;
		}
		return cattle1(n - 1) + cattle1(n - 3);
	}

	public static int cattle(int n) {
		if (n < 1) {
			System.out.println("error ");
			result = n;
		} else if (n == 1) {
			result = a;
		} else if (n == 2) {
			result = b;
		} else if (n == 3) {
			result = c;
		} else {
			result = cattle(n - 1) + cattle(n - 3);
		}
		return result;
	}
}
