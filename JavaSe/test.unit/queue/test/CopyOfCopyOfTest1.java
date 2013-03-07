package queue.test;

public class CopyOfCopyOfTest1 {

	/**/
	public static void main(String[] args) {
		CopyOfTest1.main(args);
		String[] str = new String[] { "a", "b", "c", "d", "e" };
		for (String s : str) {
			CopyOfTest1.add(s);
		}
	}
}
