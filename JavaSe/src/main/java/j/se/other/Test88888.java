package j.se.other;

public class Test88888 {

	public static void main(String[] args) {
		StringBuffer result = new StringBuffer();
		StringBuffer sb1 = null;
		System.out.println(sb1);
		result.append(sb1);
		result.insert(0, "123");
		System.out.println(result);
	}
}
