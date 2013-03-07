package j.se.threadlocale.demo;

import java.util.Random;

/**
 * 
 * @author hejie
 *
 */
public class TreadLocalDemo implements Runnable {
	private final static ThreadLocal<Student> studentLocal = new ThreadLocal<Student>();

	/**
	 * 
	 */
	public void accessStudent() {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println(currentThreadName + " is running!");
		Random random = new Random();
		int age = random.nextInt(100);
		System.out.println("thread " + currentThreadName + " set age to:" + age);
		Student student = getStudent();
		System.err.println("student : " + student.hashCode());
		student.setAge(age);
		System.out.println("thread " + currentThreadName + " first  read age is:" + student.getAge());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println("thread " + currentThreadName + " second read age is:" + student.getAge());
	}

	protected Student getStudent() {
		Student student = studentLocal.get();
		if (student == null) {
			System.out.println("�����ˣ�");
			student = new Student();
			studentLocal.set(student);
		}
		return student;
	}

	@Override
	public void run() {
		accessStudent();
	}

}
