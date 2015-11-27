package j.se.other;

public class B {

	public void test() {
		Throwable ex = new Throwable();
		StackTraceElement[] stackElements = ex.getStackTrace();
		if (stackElements != null) {
			for (int i = 0; i < stackElements.length; i++) {
				System.out.println(stackElements[i].getClassName());
				System.out.println(stackElements[i].getFileName());
				System.out.println(stackElements[i].getLineNumber());
				System.out.println(stackElements[i].getMethodName());
				System.out.println("-----------------------------------");
			}
		}
		StackTraceElement st[] = Thread.currentThread().getStackTrace();
		for (int i = 0; i < st.length; i++) {
			System.out.println("Thread-----------------------------------");
			System.out.println(st[i].getClassName());
			System.out.println(st[i].getFileName());
			System.out.println(st[i].getLineNumber());
			System.out.println(st[i].getMethodName());
			System.out.println("Thread-----------------------------------");
		}
	}

	public void test1() {
		StackTraceElement st[] = Thread.currentThread().getStackTrace();
		for (int i = 0; i < st.length; i++) {
			System.out.println(st[i].getClassName());
			System.out.println(st[i].getFileName());
			System.out.println(st[i].getLineNumber());
			System.out.println(st[i].getMethodName());
			System.out.println("Thread-----------------------------------");
		}
	}
}
