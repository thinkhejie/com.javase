package j.se.concurrency.collections;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DelayQueueDemo {
	public static void main(String[] args) {
		/*
		  DelayQueue是一个BlockingQueue，其特化的参数是Delayed。（不了解BlockingQueue的同学，先去了解BlockingQueue再看本文）
		
		  Delayed扩展了Comparable接口，比较的基准为延时的时间值，Delayed接口的实现类getDelay的返回值应为固定值（final）。
		  
		  DelayQueue内部是使用PriorityQueue实现的。

		  DelayQueue = BlockingQueue + PriorityQueue + Delayed
		 */
		DelayQueue dq = new DelayQueue();
		//dq.add(e);
		int STUDENT_SIZE = 45;
		Random r = new Random();
		DelayQueue<Student> students = new DelayQueue<Student>();
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < STUDENT_SIZE; i++) {
			students.put(new Student("学生" + (i + 1), 3000 + r.nextInt(9000)));
		}
		students.put(new Student.EndExam(12000, exec));//1200为考试结束时间                                             
		exec.execute(new Teacher(students, exec));
	}
}
