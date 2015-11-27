package j.jdbc.db.common;

public class Animal {

	public String noise() {
		return "pee";
	}

	@org.junit.Test
	public void tt() {
		String str = "Kabcdefgaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaabcdefgaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaabcdefgaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaah";
		if (str.length() > 255) {
			System.out.println(str.substring(0, 255));
		} else {
			System.out.println("else:  " + str.substring(0, 255));
		}
	}
}
